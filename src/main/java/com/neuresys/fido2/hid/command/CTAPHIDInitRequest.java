package com.neuresys.fido2.hid.command;


import java.util.Arrays;
import java.util.List;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;
import com.neuresys.fido2.hid.cte.CtapMiscCte;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;
import com.neuresys.fido2.hid.packet.HIDPacket;
import com.neuresys.fido2.utils.ByteUtils;

public class CTAPHIDInitRequest implements CTAPHIDRequest{

	protected byte [] nonce=new byte[8];
	public CTAPHIDInitRequest()
	{
		ByteUtils.feednonce(nonce);
		
	}
	
	public byte[] getNonce() {
		return nonce;
	}

	@Override
	public List<HIDPacket> getPacket()
	{
		HIDInitializationPacket initPacket=new HIDInitializationPacket(CtapMiscCte.BROADCAST_ID,
				CtapHIDCmd.CTAPHID_INIT,
				nonce.length,nonce
				);
		return Arrays.asList(initPacket);
	}
}
