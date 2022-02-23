package com.neuresys.fido2.ctap2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatorGetInfo {

	@JsonProperty("1")
	List<String> versions;
	@JsonProperty("2")
	List<String> extensions;
	@JsonProperty("3")
	String aaguid ;
	@JsonProperty("4")
	Map<String,String> options=new HashMap<>() ;
	@JsonProperty("5")
	Integer maxMsgSize;
	@JsonProperty("6")
	List<Integer> pinProtocols=new ArrayList<>();
	
	public List<String> getVersions() {
		return versions;
	}

	public void setVersions(List<String> versions) {
		this.versions = versions;
	}

	public List<String> getExtensions() {
		return extensions;
	}

	public void setExtensions(List<String> extensions) {
		this.extensions = extensions;
	}

	public String getAaguid() {
		return aaguid;
	}

	public void setAaguid(String aaguid) {
		this.aaguid = aaguid;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public Integer getMaxMsgSize() {
		return maxMsgSize;
	}

	public void setMaxMsgSize(Integer maxMsgSize) {
		this.maxMsgSize = maxMsgSize;
	}

	public List<Integer> getPinProtocols() {
		return pinProtocols;
	}

	public void setPinProtocols(List<Integer> pinProtocols) {
		this.pinProtocols = pinProtocols;
	}

	@Override
	public String toString() {
		return "AuthenticatorGetInfo [versions=" + versions + ", extensions=" + extensions + ", aaguid=" + aaguid
				+ ", options=" + options + ", maxMsgSize=" + maxMsgSize + ", pinProtocols=" + pinProtocols + "]";
	}
	
}
