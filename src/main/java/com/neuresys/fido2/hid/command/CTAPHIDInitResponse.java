package com.neuresys.fido2.hid.command;

import java.util.Arrays;

import com.neuresys.fido2.hid.message.Message;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;
import com.neuresys.fido2.utils.ByteUtils;

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

	byte [] nonce=new byte[8];
	byte [] channelId=new byte[4];
	byte procotolVersion;
	byte majorDeviceVersionNumber;
	byte minorDeviceVersionNumber;
	byte buildDeviceVersionNumber;
	byte capabilities;
	public CTAPHIDInitResponse(Message message)
	{
		byte [] payload=message.getPayload();
		for (int i=0;i<8;i++) nonce[i]=payload[i];
		for (int i=0;i<4;i++) channelId[i]=payload[i+8];
		procotolVersion=payload[12];
		majorDeviceVersionNumber=payload[13];
		minorDeviceVersionNumber=payload[14];
		buildDeviceVersionNumber=payload[15];
		capabilities=payload[16];
		
		
	}
	public byte[] getNonce() {
		return nonce;
	}
	public void setNonce(byte[] nonce) {
		this.nonce = nonce;
	}
	public byte[] getChannelId() {
		return channelId;
	}
	public void setChannelId(byte[] channelId) {
		this.channelId = channelId;
	}
	public byte getProcotolVersion() {
		return procotolVersion;
	}
	public void setProcotolVersion(byte procotolVersion) {
		this.procotolVersion = procotolVersion;
	}
	public byte getMajorDeviceVersionNumber() {
		return majorDeviceVersionNumber;
	}
	public void setMajorDeviceVersionNumber(byte majorDeviceVersionNumber) {
		this.majorDeviceVersionNumber = majorDeviceVersionNumber;
	}
	public byte getMinorDeviceVersionNumber() {
		return minorDeviceVersionNumber;
	}
	public void setMinorDeviceVersionNumber(byte minorDeviceVersionNumber) {
		this.minorDeviceVersionNumber = minorDeviceVersionNumber;
	}
	public byte getBuildDeviceVersionNumber() {
		return buildDeviceVersionNumber;
	}
	public void setBuildDeviceVersionNumber(byte buildDeviceVersionNumber) {
		this.buildDeviceVersionNumber = buildDeviceVersionNumber;
	}
	public byte getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(byte capabilities) {
		this.capabilities = capabilities;
	}
	@Override
	public String toString() {
		return "CTAPHIDInitResponse [nonce=" +  ByteUtils.convertBytesToHex(nonce) + ", channelId=" + Arrays.toString(channelId)
				+ ", procotolVersion=" + procotolVersion + ", majorDeviceVersionNumber=" + majorDeviceVersionNumber
				+ ", minorDeviceVersionNumber=" + minorDeviceVersionNumber + ", buildDeviceVersionNumber="
				+ buildDeviceVersionNumber + ", capabilities=" + capabilities + "]";
	}
	
}
