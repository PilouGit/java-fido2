package com.neuresys.fido2.hid.command;

import java.util.Arrays;
import java.util.List;

import com.neuresys.fido2.hid.ctap2.cte.CtapCmd;
import com.neuresys.fido2.hid.cte.CtapHIDCmd;
import com.neuresys.fido2.hid.cte.CtapMiscCte;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;
import com.neuresys.fido2.hid.packet.HIDPacket;

public class CTAPHIDCborRequest implements CTAPHIDRequest{

	private byte[] cid;
	private CtapCmd ctapCmd;
	private byte[] cbor;

	public CTAPHIDCborRequest ( byte[] cid, CtapCmd ctapCmd, byte [] cbor)
	{
		this.cid=cid;
		this.ctapCmd=ctapCmd;
		this.cbor=cbor;
	}

	@Override
	public List<HIDPacket> getPacket() {
		byte [] message=new byte[1+cbor.length];
		message[0]=ctapCmd.getValue();
		for (int i=0;i<cbor.length;i++) message[i+1]=cbor[i];
		HIDInitializationPacket initPacket=new HIDInitializationPacket(cid,
				CtapHIDCmd.CTAPHID_CBOR,
				message.length,message
				);
		return Arrays.asList(initPacket);
	}
}
