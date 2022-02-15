package com.neuresys.fido2.hid.packet;

import org.apache.commons.lang3.ArrayUtils;

import com.neuresys.fido2.cte.CtapHIDCmd;
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
	private byte[] payload;
	private byte[] packet;

	public HIDInitializationPacket(byte [] cid,CtapHIDCmd cmd, int length,byte [] payload)
	{
		this.cid=cid;
		this.cmd=cmd;
		this.length=length;
		this.payload=payload;
		this.packet=new byte[4+3+payload.length];
		for (int i=0;i<4;i++) this.packet[i]=cid[i];
		
		
		this.packet[4] = (byte)((byte) 0x80 + this.cmd.getCmdByte());
		byte [] lengthInByte=ByteUtils.convertIntToByteArray(length);
		this.packet[5]=lengthInByte[2];
		this.packet[6]=lengthInByte[3];
		for (int i=0;i<payload.length;i++)this.packet[7+i]=payload[i];
		
	}
	public HIDInitializationPacket(byte [] packet)
	{
		this.packet=packet;
		for (int i=0;i<4;i++) this.cid[i]=packet[i];
		this.cmd=CtapHIDCmd.valueOf((byte) (packet[4] & ~(1 << 7)));
		
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
		return packet;
	}

	public String toString()
	{
		return "HIDInitializationPacket"+ByteUtils.convertBytesToHex(this.packet);
	}

}
