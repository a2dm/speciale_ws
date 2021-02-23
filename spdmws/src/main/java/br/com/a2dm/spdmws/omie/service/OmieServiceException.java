package br.com.a2dm.spdmws.omie.service;

public class OmieServiceException extends Exception {

	private static final long serialVersionUID = -4449375933708667161L;

	public OmieServiceException() {
	}

	public OmieServiceException(String message) {
		super(message);
	}

	public OmieServiceException(Throwable cause) {
		super(cause);
	}

	public OmieServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmieServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
