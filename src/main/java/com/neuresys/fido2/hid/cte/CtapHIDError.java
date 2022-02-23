package com.neuresys.fido2.hid.cte;

public class CtapHIDError {
	public static byte ERR_INVALID_CMD = 0x01; // The command in the request is invalid
	public static byte ERR_INVALID_PAR = 0x02; // The parameter(s) in the request is invalid
	public static byte ERR_INVALID_LEN = 0x03; // The length field (BCNT) is invalid for the request
	public static byte ERR_INVALID_SEQ = 0x04; // The sequence does not match expected value
	public static byte ERR_MSG_TIMEOUT = 0x05;// The message has timed out
	public static byte ERR_CHANNEL_BUSY = 0x06; // The device is busy for the requesting channel
	public static byte ERR_LOCK_REQUIRED = 0x0A; // Command requires channel lock
	public static byte ERR_INVALID_CHANNEL = 0x0B; // Reserved (Removed)
	public static byte ERR_OTHER = 0x7F; // Unspecified error
}
