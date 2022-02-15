package com.neuresys.fido2.hid;

import org.apache.commons.lang3.ArrayUtils;

public class PingPacketRequest {

	private byte[] data;

	public PingPacketRequest(byte [] channelId,String hello)
	{
		this.data=new byte[] {
				channelId[0],channelId[1],channelId[2],channelId[3],
				 (byte) ((byte) 0x80 + HIDCte.CTAP_CMD_PING),
				 0,(byte)hello.length()};
		this.data=ArrayUtils.addAll(this.data, hello.getBytes());
		
		}

	public byte[] getData() {
		return data;
	}
	
}
