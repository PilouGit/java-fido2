package com.neuresys.fido2.hid.cte;

public class CtapMiscCte {

	public static final byte [] BROADCAST_ID = {(byte) 0xff,(byte) 0xff,(byte) 0xff,(byte) 0xff};
	public static int MESSAGE_SIZE = 64;
	public static int HIDINITHEADERSIZE = 7;
	public static int HIDINITPAYLOADSIZE = MESSAGE_SIZE-HIDINITHEADERSIZE;
	
	public static int HIDCONTINUATIONHEADERSIZE = 5;
	public static int HIDCONTINUATIONSIZE = MESSAGE_SIZE-HIDCONTINUATIONHEADERSIZE;
	
}
