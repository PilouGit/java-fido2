package com.neuresys.fido2.hid.packet;

import com.neuresys.fido2.cte.CtapHIDCmd;
import com.neuresys.fido2.utils.ByteUtils;

/*   """HID Continuation Packet consists of:
    Offset 	Length 	Mnemonic 	Description
        0 	4 	    CID 	Channel identifier
        4 	1 	    SEQ 	Packet sequence 0x00..0x7f (bit 7 always cleared)
        5 	(s - 5) DATA 	Payload data (s is equal to the fixed packet size)
    """
    */
public class HIDContinuationPacket implements HIDPacket{
	
	private byte[] cid;
	private byte[] payload;
	private byte[] packet;
	private int seqNumber;

	public HIDContinuationPacket(byte [] cid, int seqNumber,byte [] payload)
	{
		this.cid=cid;
		this.payload=payload;
		this.packet=new byte[4+1+payload.length];
		this.seqNumber=seqNumber;
		for (int i=0;i<4;i++) this.packet[i]=cid[i];
		
		this.packet[4] = (byte) (this.seqNumber & ~(1 << 7));
				 
		for (int i=0;i<payload.length;i++)this.packet[5+i]=payload[i];
		
	}

	public byte[] getCid() {
		return cid;
	}

	public byte[] getPayload() {
		return payload;
	}

	@Override
	public byte[] getPacket() {
		return packet;
	}

	public int getSeqNumber() {
		return seqNumber;
	}
	
	
}
