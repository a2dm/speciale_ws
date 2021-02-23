package br.com.a2dm.spdmws.omie.api;

import java.util.ArrayList;
import java.util.List;

public class OmieApiPayload {

	private String app_key;
	private String app_secret;
	private String call;
	private List<Object> param;
	
	public OmieApiPayload() {
		this.param = new ArrayList<>();
	}

	public String getApp_key() {
		return app_key;
	}
	
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	
	public String getApp_secret() {
		return app_secret;
	}
	
	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}
	
	public String getCall() {
		return call;
	}
	
	public void setCall(String call) {
		this.call = call;
	}
	
	public List<Object> getParam() {
		return param;
	}
	
	public void setParam(List<Object> param) {
		this.param = param;
	}
	
	public void addParam(Object param) {
		this.param.add(param);
	}
}
