package br.com.a2dm.spdmws.exception;

public class ExceptionUtils {

	private ExceptionUtils() {
	}
	
	public static ApiException handlerApiException(Exception e){
		if(e instanceof ApiException) {
			return (ApiException)e;
		}
		return new ApiException(500, e.getMessage(), e);
	}

}
