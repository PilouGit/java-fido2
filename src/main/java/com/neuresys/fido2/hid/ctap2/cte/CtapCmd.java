package com.neuresys.fido2.hid.ctap2.cte;

public enum CtapCmd {

	authenticatorGetInfo((byte)0x04);

	private byte value;

	CtapCmd(byte value) {
		this.value=value;
	}

	public byte getValue() {
		return value;
	}
	
}
