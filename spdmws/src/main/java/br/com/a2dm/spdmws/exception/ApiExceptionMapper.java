package br.com.a2dm.spdmws.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException>{

	@Override
	public Response toResponse(ApiException e) {
		
		this.logException(e);
		
		return Response
				.status(e.getStatusCode())
				.type(MediaType.APPLICATION_JSON)
				.entity(ExceptionUtils.toJson(e))
				.build();
	}
	
	private void logException(ApiException apiException) {
		apiException.printStackTrace();
	}

}
