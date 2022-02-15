package com.neuresys.fido2.hid;

import java.util.Arrays;

public class InitPacketResponse {

	byte[] channelId=new byte[4];
	byte command;
	byte [] byteCount=new byte[2];
	byte [] nonce=new byte[8];
	byte [] newChannelId=new byte[4];
	byte protocol;
	byte majorVersion;
	byte minor;
	byte build;
	byte capabilities;
	public InitPacketResponse(byte [] data)
	{
		channelId[0]=data[0];
		channelId[1]=data[1];
		channelId[2]=data[2];
		channelId[3]=data[3];
		command=data[4];
		byteCount[0]=data[5];
		byteCount[1]=data[6];
		newChannelId[0]=data[15];
		newChannelId[1]=data[16];
		newChannelId[2]=data[17];
		newChannelId[3]=data[18];
				  
				   
	}
	public byte[] getNewChannelId() {
		return newChannelId;
	}
	@Override
	public String toString() {
		return "InitPacketResponse [ newChannelId:"+Arrays.toString(newChannelId)+"]";
	}
	
	
}
