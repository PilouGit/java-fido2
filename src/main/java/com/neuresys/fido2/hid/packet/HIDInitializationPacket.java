package com.neuresys.fido2.hid.packet;

import org.apache.commons.lang3.ArrayUtils;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;
import com.neuresys.fido2.hid.cte.CtapMiscCte;
import com.neuresys.fido2.utils.ByteUtils;

/*
 *   Offset 	Length 	Mnemonic 	Description
            0 	4 	    CID 	Channel identifier
            4 	1 	    CMD 	Command identifier (bit 7 always set)
            5 	1 	    BCNTH 	High part of payload length
            6 	1 	    BCNTL 	Low part of payload length
            7 	(s - 7) DATA 	Payload data (s is equal to the fixed packet size)

 */
public class HIDInitializationPacket implements HIDPacket {
	private byte[] cid=new byte[4];
	private CtapHIDCmd cmd;
	private int length;
	private byte[] payload=new byte [CtapMiscCte.HIDINITPAYLOADSIZE];

	public HIDInitializationPacket()
	{
		
	}
	/*public HIDInitializationPacket(byte [] cid,CtapHIDCmd cmd, int length,byte [] packet)
	{
		this.cid=cid;
		this.cmd=cmd;
		this.length=length;
		for (int i=0;i<4;i++) this.packet[i]=cid[i];
		
		
		this.packet[4] = this.cmd.getCmdByte();
		byte [] lengthInByte=ByteUtils.convertIntToByteArray(length);
		this.packet[5]= (byte) (payload.length >> 8) ;
		this.packet[6]=(byte) (payload.length);
		for (int i=0;i<payload.length;i++)this.packet[7+i]=payload[i];
		
	}*/
	public HIDInitializationPacket(byte [] cid,CtapHIDCmd cmd, int length,byte [] payload)
	{
		this.cid=cid;
		this.cmd=cmd;
		this.length=length;
		this.payload=payload;
	}
	
	public HIDInitializationPacket(byte [] packet)
	{
		
		for (int i=0;i<4;i++) this.cid[i]=packet[i];
		this.cmd=CtapHIDCmd.valueOf((byte) (packet[4] & ~(1 << 7)));
		this.length=ByteUtils.fromByteArray(new byte[]{0,0,packet[5],packet[6]});
		int buffsize=0;
		if (length>(64-7))
			buffsize=64-7; else
				buffsize=length;
			
		System.err.println(buffsize);
		System.err.println(packet.length);
		this.payload=new byte[buffsize];
		for (int i=0;i<buffsize;i++) {
			this.payload[i]=packet[7+i];
		}
		
		
	}
	public boolean isFinish()
	{
		return length<64;
	}
	public byte[] getCid() {
		return cid;
	}

	public CtapHIDCmd getCmd() {
		return cmd;
	}

	public int getLength() {
		return length;
	}

	

	public byte[] getPayload() {
		return payload;
	}
	@Override
	public byte[] getPacket() {
		 byte[] packet=new byte[CtapMiscCte.MESSAGE_SIZE];
		 System.arraycopy(cid, 0, packet, 0, cid.length);
		 packet[4] = this.cmd.getCmdByte();
		 packet[5]= (byte) (payload.length >> 8) ;
		 packet[6]=(byte) (payload.length);
		 System.arraycopy(payload, 0, packet, 7, payload.length);
		 return packet;
		 
	}

	public String toString()
	{
		return "HIDInitializationPacket("+this.cmd+")"+ByteUtils.convertBytesToHex(getPacket());
	}

}
