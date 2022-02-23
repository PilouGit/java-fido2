package com.neuresys.fido2.hid;

import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesSpecification;
import org.hid4java.jna.HidApi;
import org.junit.Test;

import com.neuresys.fido2.hid.command.CTAPHIDCborRequest;
import com.neuresys.fido2.hid.command.CTAPHIDCborResponse;
import com.neuresys.fido2.hid.command.CTAPHIDInitRequest;
import com.neuresys.fido2.hid.command.CTAPHIDInitResponse;
import com.neuresys.fido2.hid.ctap2.cte.CtapCmd;
import com.neuresys.fido2.hid.message.Message;
import com.neuresys.fido2.hid.packet.HIDContinuationPacket;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;
import com.neuresys.fido2.hid.packet.HIDPacket;
import com.neuresys.fido2.hid.packet.HIDPacketFactory;

public class HIDExemple {
	private static int CTAP_MAX_REPORT_LEN = 64;

	@Test
	public void test() {
		 HidApi.logTraffic = true;
 HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();

		    // Use manual start
		    hidServicesSpecification.setAutoStart(false);

		    // Use data received events
		    hidServicesSpecification.setAutoDataRead(true);
		    hidServicesSpecification.setDataReadInterval(500);

		    // Get HID services using custom specification
		    HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);

		    // Manually start HID services
		    hidServices.start();
		    HidDevice fidoDevice = null;
		    for (HidDevice hidDevice : hidServices.getAttachedHidDevices()) {
		    
		      if (hidDevice.getProduct().contains("FIDO2")) {
		        fidoDevice = hidDevice;
		        break;
		      }
		    }
		    fidoDevice.open();
		    CTAPHIDInitRequest request=new CTAPHIDInitRequest();
		    
		    int bytesWritten = fidoDevice.write(request.getPacket().get(0).getPacket(), CTAP_MAX_REPORT_LEN, (byte) 0x00, true);
		    byte [] buff=new byte[CTAP_MAX_REPORT_LEN];
		    fidoDevice.read(buff);
		    HIDInitializationPacket response=new HIDInitializationPacket(buff);
		    Message mess=new Message(response);
		    CTAPHIDInitResponse responseCmd=new CTAPHIDInitResponse(mess);
		    byte [] cborData=new byte[0];
		    CTAPHIDCborRequest requestCbor=new  CTAPHIDCborRequest(responseCmd.getChannelId(),
		    		CtapCmd.authenticatorGetInfo,cborData
		    		);
		    fidoDevice.write(requestCbor.getPacket().get(0).getPacket(), CTAP_MAX_REPORT_LEN, (byte) 0x00, true);
		    Message message=null;
		    while (fidoDevice.read(buff)!=-1)
		    {
		    	HIDPacket packet1 = HIDPacketFactory.INSTANCE.getHIDPacket(buff);
		    	if (packet1 instanceof HIDInitializationPacket)
		    	{
		    		HIDInitializationPacket initPacket=(HIDInitializationPacket) packet1;
		    		message=new Message(initPacket);
		    		if (message.isFinish())
		    		{
		    			   System.out.println(packet1);
		    		}
		    	}else
		    	{
		    		HIDContinuationPacket continuousPacket=(HIDContinuationPacket) packet1;
		    		message.appendContinuationPacket(continuousPacket);
		    		 System.out.println(message);
		    		 CTAPHIDCborResponse responseCbor=new CTAPHIDCborResponse(message);
		 	    	System.out.println(responseCbor);
		    	}
		    	
			 
		    }
		  /*  HIDPacket packet1 = HIDPacketFactory.INSTANCE.getHIDPacket(buff);
		    System.out.println(packet1);
		    fidoDevice.read(buff);
		    HIDPacket packet2 = HIDPacketFactory.INSTANCE.getHIDPacket(buff);
		    System.out.println(packet2);
		    HIDPacket packet3 = HIDPacketFactory.INSTANCE.getHIDPacket(buff);
		    System.out.println(packet3);     
		    
		    HIDPacket packet4 = HIDPacketFactory.INSTANCE.getHIDPacket(buff);
		    System.out.println(packet4);     
		    
		    
		    response=new HIDInitializationPacket(buff);
		    CTAPHIDCborResponse responseCbor=new CTAPHIDCborResponse(response);
	    	System.out.println(responseCbor);*/
		    
		    
	}
}
