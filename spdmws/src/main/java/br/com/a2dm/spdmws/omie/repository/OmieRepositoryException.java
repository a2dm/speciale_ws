package br.com.a2dm.spdmws.omie.repository;

public class OmieRepositoryException extends Exception {

	private static final long serialVersionUID = -5114824598729909325L;

	public OmieRepositoryException() {
	}

	public OmieRepositoryException(String message) {
		super(message);
	}

	public OmieRepositoryException(Throwable cause) {
		super(cause);
	}

	public OmieRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmieRepositoryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
