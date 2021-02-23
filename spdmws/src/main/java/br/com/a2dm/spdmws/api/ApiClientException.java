package br.com.a2dm.spdmws.api;

public class ApiClientException extends RuntimeException {

	private static final long serialVersionUID = 6805893808179029633L;

	public ApiClientException() {
	}

	public ApiClientException(String message) {
		super(message);
	}

	public ApiClientException(Throwable cause) {
		super(cause);
	}

	public ApiClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
