package com.neuresys.fido2.hid;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;

public class CtapHidRequest {

	byte [] cid;
	CtapHIDCmd ctapHidCmd;
	int size;
	byte [] payload;
	
	public CtapHidRequest(byte[] cid,CtapHIDCmd ctapHidCmd, int size, byte[] payload) {
		super();
		this.cid=cid;
		this.ctapHidCmd = ctapHidCmd;
		this.size = size;
		this.payload = payload;
	}
	public CtapHIDCmd getCtapHidCmd() {
		return ctapHidCmd;
	}
	public void setCtapHidCmd(CtapHIDCmd ctapHidCmd) {
		this.ctapHidCmd = ctapHidCmd;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public byte[] getPayload() {
		return payload;
	}
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	
}
