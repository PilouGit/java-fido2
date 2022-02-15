package com.neuresys.fido2.hid.command;

/* CMD 	CTAPHID_INIT
        BCNT 	17 (see note below)
        DATA 	8-byte nonce
        DATA+8 	4-byte channel ID
        DATA+12 	CTAPHID protocol version identifier
        DATA+13 	Major device version number
        DATA+14 	Minor device version number
        DATA+15 	Build device version number
        DATA+16 	Capabilities flags
        */
public class CTAPHIDInitResponse {

	public CTAPHIDInitResponse(byte [] data)
	{
		
	}
}
