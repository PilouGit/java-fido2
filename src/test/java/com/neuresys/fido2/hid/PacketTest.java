package com.neuresys.fido2.hid;

import org.junit.Test;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;

public class PacketTest {

	@Test 
	public void testInitPacket()
	{
		byte[] nonce = new byte[8];
		nonce[0]=1;
		nonce[1]=1;
		nonce[7]=3;
		
		HIDInitializationPacket packet=new HIDInitializationPacket
				(
						new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff},
						CtapHIDCmd.CTAPHID_INIT,
						nonce.length,nonce
				);
		System.out.println(packet);
				
	}
}
