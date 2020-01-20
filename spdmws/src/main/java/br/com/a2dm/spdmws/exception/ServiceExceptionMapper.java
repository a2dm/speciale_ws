package br.com.a2dm.spdmws.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.a2dm.spdm.exception.ServiceException;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException>{

	@Override
	public Response toResponse(ServiceException e) {
		e.printStackTrace();
		
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
				.type(MediaType.APPLICATION_JSON)
				.entity("Problemas ao executar opreração")
				.build();
	}

}
