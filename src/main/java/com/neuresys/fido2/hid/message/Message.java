package com.neuresys.fido2.hid.message;

import com.neuresys.fido2.hid.cte.CtapHIDCmd;
import com.neuresys.fido2.hid.cte.CtapMiscCte;
import com.neuresys.fido2.hid.packet.HIDContinuationPacket;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;

public class Message {
	private byte[] cid=new byte[4];
	private CtapHIDCmd cmd;
	private byte[] payload=new byte [0];
	private int remainingBytes;
	private int writtenBytes;
	public Message(byte[] cid, CtapHIDCmd cmd, byte[] payload) {
		super();
		this.cid = cid;
		this.cmd = cmd;
		this.payload = payload;
		this.remainingBytes=0;
		this.writtenBytes=0;
	}
	public Message(HIDInitializationPacket packet)
	{
		this.cid=packet.getCid();
		this.cmd=packet.getCmd();
		this.payload=new byte [packet.getLength()];
		this.remainingBytes = this.payload.length -packet.getPayload().length;
		writtenBytes=packet.getPayload().length;
		System.arraycopy(packet.getPayload(), 0, payload, 0, packet.getPayload().length);
	}
	public void appendContinuationPacket(HIDContinuationPacket packet)
	{
		 byte [] continuationPayLoad=packet.getPayload();
         if (remainingBytes > CtapMiscCte.HIDCONTINUATIONSIZE){
	            System.arraycopy(continuationPayLoad, 0, payload, writtenBytes,continuationPayLoad.length);
	            writtenBytes+=CtapMiscCte.HIDCONTINUATIONSIZE;
	            remainingBytes = remainingBytes - CtapMiscCte.HIDCONTINUATIONSIZE;
	            
		  }
	        else{
	        	 System.arraycopy(continuationPayLoad, 0, payload, writtenBytes,remainingBytes);
		         remainingBytes = 0;
			  
	        }
	}
	public boolean isFinish()
	{
		return this.remainingBytes==0;
	}
	public byte[] getCid() {
		return cid;
	}
	public void setCid(byte[] cid) {
		this.cid = cid;
	}
	public CtapHIDCmd getCmd() {
		return cmd;
	}
	public void setCmd(CtapHIDCmd cmd) {
		this.cmd = cmd;
	}
	public byte[] getPayload() {
		return payload;
	}
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	
}
