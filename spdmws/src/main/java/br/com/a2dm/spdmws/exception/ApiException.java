package br.com.a2dm.spdmws.exception;

public class ApiException extends Exception {

	private static final long serialVersionUID = 4583551151614451118L;
	
	private Integer statusCode;
	
	public ApiException(Integer statusCode, String message, Exception exception) {
		super(message, exception);
		this.statusCode = statusCode;
	}
	
	public ApiException(Integer statusCode, String message) {
		this(statusCode, message, null);
	}
	
	public ApiException(Integer statusCode, Exception exception) {
		this(statusCode, "Ocorreu um erro ao executar operação", exception);
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	

}
