package br.com.a2dm.spdmws.exception;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ExceptionUtils {

	private ExceptionUtils() {
	}
	
	public static ApiException handlerApiException(Exception e){
		if(e instanceof ApiException) {
			return (ApiException)e;
		}
		return new ApiException(500, e.getMessage(), e);
	}
	
	public static JSONObject toJson(ApiException apiException) {
		try {
			Throwable rootCause = org.apache.commons.lang.exception.ExceptionUtils.getRootCause(apiException);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("statusCode", apiException.getStatusCode());
			jsonObject.put("message", rootCause.getMessage());
			return jsonObject;		
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

}
