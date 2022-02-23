package com.neuresys.fido2.hid.cte;

public enum CtapHIDCmd {
	
	CTAPHID_MSG ((byte) (0x80|0x03)),
	CTAPHID_CBOR((byte) (0x80|0x10)),
	CTAPHID_INIT ( (byte) (0x80|0x06)),
	CTAPHID_PING ( (byte) (0x80|0x01)),
	CTAPHID_CANCEL( (byte) (0x80|0x11)),
	CTAPHID_ERROR ( (byte) (0x80|0x3F)),
	CTAPHID_KEEPALIVE ( (byte) (0x80|0x3B));
	
;

private byte cmdByte;

CtapHIDCmd(byte cmdByte) {
	this.cmdByte=cmdByte;
}
public static CtapHIDCmd valueOf(byte data)
{
	switch (data)
	{
	case 0x03: return CtapHIDCmd.CTAPHID_MSG;
	case 0x10: return CtapHIDCmd.CTAPHID_CBOR;
	case 0x06: return CtapHIDCmd.CTAPHID_INIT;
	case 0x01: return CtapHIDCmd.CTAPHID_PING;
	case 0x11: return CtapHIDCmd.CTAPHID_CANCEL;
	case 0x3F: return CtapHIDCmd.CTAPHID_ERROR;
	case 0x3B:return CtapHIDCmd.CTAPHID_KEEPALIVE;
	default: throw new IllegalArgumentException("Unknwon operation "+data);
	}
	
}
public byte getCmdByte() {
	return cmdByte;
}

}
