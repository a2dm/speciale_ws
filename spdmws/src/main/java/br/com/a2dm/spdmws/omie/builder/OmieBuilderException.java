package br.com.a2dm.spdmws.omie.builder;

public class OmieBuilderException extends RuntimeException {

	private static final long serialVersionUID = -916669982799065333L;

	public OmieBuilderException() {
	}

	public OmieBuilderException(String message) {
		super(message);
	}

	public OmieBuilderException(Throwable cause) {
		super(cause);
	}

	public OmieBuilderException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmieBuilderException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
