package com.neuresys.fido2.hid;

import org.apache.commons.lang3.ArrayUtils;

public class CBorPacketRequest {
	private byte[] data;

	public CBorPacketRequest(byte [] channelId,byte [] buffer)
	{
		this.data=new byte[] {
				channelId[0],channelId[1],channelId[2],channelId[3],
				 (byte) ((byte) 0x80 + HIDCte.CTAP_CMD_CBOR),
				 0,(byte)buffer.length};
		this.data=ArrayUtils.addAll(this.data, buffer);
		
		}
}
