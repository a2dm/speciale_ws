package br.com.a2dm.spdmws.omie.api;

public class OmieApiError {

	private String faultstring;
	private String faultcode;
	
	public OmieApiError() {
	}
	
	public String getFaultstring() {
		return faultstring;
	}
	
	public void setFaultstring(String faultstring) {
		this.faultstring = faultstring;
	}
	
	public String getFaultcode() {
		return faultcode;
	}
	
	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}
}
