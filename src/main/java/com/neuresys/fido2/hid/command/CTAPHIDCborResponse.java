package com.neuresys.fido2.hid.command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.neuresys.fido2.ctap2.AuthenticatorGetInfo;
import com.neuresys.fido2.hid.cte.CtapHIDStatusCode;
import com.neuresys.fido2.hid.message.Message;
import com.neuresys.fido2.hid.packet.HIDInitializationPacket;
import com.neuresys.fido2.utils.ByteUtils;
import com.upokecenter.cbor.CBOREncodeOptions;
import com.upokecenter.cbor.CBORObject;

public class CTAPHIDCborResponse {

	byte [] cborData;
	CtapHIDStatusCode statusCode;
	
	public CTAPHIDCborResponse(Message message)
	{
		byte [] payload=message.getPayload();
		statusCode=CtapHIDStatusCode.getByValue(payload[0]);
		this.cborData=new byte [payload.length-1];
		for (int i=0;i<this.cborData.length;i++) cborData[i]=payload[i+1]; 
					
		System.err.println("cbor  "+ByteUtils.convertBytesToHex(this.cborData));
		 ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());
		 try {
			  //JsonNode jsonNode = objectMapper.readTree(cborData);
			  //AuthenticatorGetInfo car = objectMapper.treeToValue(jsonNode,AuthenticatorGetInfo.class);
			 AuthenticatorGetInfo car= objectMapper.readValue(cborData, AuthenticatorGetInfo.class) ;
			 System.err.println(car);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//  CBOREncodeOptions CBOREncodeOptions.DefaultCtap2Canonical;
	//	CBORObject cbor = CBORObject.Read(new ByteArrayInputStream(this.cborData),CBOREncodeOptions.DefaultCtap2Canonical);
	//        System.out.println(cbor);
	        
	}

	public byte[] getCborData() {
		return cborData;
	}

	public void setCborData(byte[] cborData) {
		this.cborData = cborData;
	}

	public CtapHIDStatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CtapHIDStatusCode statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "CTAPHIDCborResponse [cborData=" + ByteUtils.convertBytesToHex(cborData) + ", statusCode=" + statusCode + "]";
	}
	
}
