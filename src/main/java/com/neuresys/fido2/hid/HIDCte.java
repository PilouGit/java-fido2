package com.neuresys.fido2.hid;

public class HIDCte {
	  public static byte CTAP_CMD_INIT = 0x06;
	  public static byte CTAP_CMD_PING = 0x01;
 public static  byte[] initialiseRequestPattern = new byte[]{
			      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, // Broadcast channel
			      (byte) ((byte) 0x80 + HIDCte.CTAP_CMD_INIT), // Initialise command
			      0x00, 0x08};
 
 public static byte CTAP_CMD_CBOR = 0x10;

}
