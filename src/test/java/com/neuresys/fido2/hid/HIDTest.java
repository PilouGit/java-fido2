package com.neuresys.fido2.hid;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbEndpoint;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbPort;
import javax.usb.UsbServices;

import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesListener;
import org.hid4java.HidServicesSpecification;
import org.hid4java.event.HidServicesEvent;
import org.junit.Test;

public class HIDTest implements HidServicesListener {

	@Test
	public void listHIDDevice() throws SecurityException, UsbException, UnsupportedEncodingException
	{
		HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();

		// Use the v0.7.0 manual start feature to get immediate attach events
		hidServicesSpecification.setAutoStart(false);

		// Get HID services using custom specification
		HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);
		hidServices.addHidServicesListener(this);

		// Manually start the services to get attachment event
		hidServices.start();

		// Provide a list of attached devices
		for (HidDevice hidDevice : hidServices.getAttachedHidDevices()) {
		  System.out.println(hidDevice);
		 //hidDevice.getFeatureReport(data, reportId)
		}
		
		    
	}

	@Override
	public void hidDeviceAttached(HidServicesEvent event) {
		System.out.println(event);
	}

	@Override
	public void hidDeviceDetached(HidServicesEvent event) {
		System.out.println(event);
	}

	@Override
	public void hidFailure(HidServicesEvent event) {
		System.out.println(event);
	}

	@Override
	public void hidDataReceived(HidServicesEvent event) {
		// TODO Auto-generated method stub
		
	}
}
