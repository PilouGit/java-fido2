package com.neuresys.fido2.hid.packet;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;
import com.neuresys.fido2.hid.cte.CtapMiscCte;
import com.neuresys.fido2.utils.ByteUtils;

/*   """HID Continuation Packet consists of:
    Offset 	Length 	Mnemonic 	Description
        0 	4 	    CID 	Channel identifier
        4 	1 	    SEQ 	Packet sequence 0x00..0x7f (bit 7 always cleared)
        5 	(s - 5) DATA 	Payload data (s is equal to the fixed packet size)
    """
    */
public class HIDContinuationPacket implements HIDPacket{
	
	private byte[] cid=new byte [4];
	private byte[] payload=new byte [CtapMiscCte.HIDCONTINUATIONSIZE];;
	private int seqNumber;

	public HIDContinuationPacket(byte [] cid, int seqNumber,byte [] payload)
	{
		this.cid=cid;
		this.seqNumber=seqNumber;
		this.payload=payload;
	
		
	}
	public HIDContinuationPacket(byte [] data)
	{
		
		for (int i=0;i<4;i++) this.cid[i]=data[i];
		this.seqNumber=data[4];
		for (int i=0;i<this.payload.length;i++)
		{
			this.payload[i]=data[5+i];
		}
	}

	

	public byte[] getCid() {
		return cid;
	}

	public byte[] getPayload() {
		return payload;
	}

	@Override
	public byte[] getPacket() {
		 byte[] packet=new byte[CtapMiscCte.MESSAGE_SIZE];
		 System.arraycopy(cid, 0, packet, 0, cid.length);
		 packet[4] = (byte) (this.seqNumber & ~(1 << 7));
		 System.arraycopy(payload, 0, packet, 5, payload.length);
		 return packet;
	}

	public int getSeqNumber() {
		return seqNumber;
	}
	
	public String toString()
	{
		return "HIDContinuationPacket("+this.seqNumber+")"+ByteUtils.convertBytesToHex(this.getPacket());
	}
	
}
