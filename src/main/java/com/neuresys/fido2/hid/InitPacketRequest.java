package com.neuresys.fido2.hid;

import org.apache.commons.lang3.ArrayUtils;

public class InitPacketRequest {


	private byte[] data;

	public InitPacketRequest(byte [] nonce)
	{
		this.data=ArrayUtils.addAll(HIDCte.initialiseRequestPattern, nonce);
	}

	public byte[] getData() {
		return data;
	}
	
}
