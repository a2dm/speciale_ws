package br.com.a2dm.spdmws.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.spdmws.dto.ClienteDTO;
import br.com.a2dm.spdmws.exception.ApiException;
import br.com.a2dm.spdmws.exception.ExceptionUtils;
import br.com.a2dm.spdmws.omie.payload.ClienteWebhookPayload;
import br.com.a2dm.spdmws.omie.service.OmieClienteService;

@Path("/clientes")
public class ClientesWS {

	@GET
	@Path("/{nomeCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClienteDTO> pesquisarClientes(@PathParam("nomeCliente") String nomeCliente) throws ApiException {
		try {
			return OmieClienteService.getInstance().pesquisarClientes(nomeCliente);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void processar(ClienteWebhookPayload clienteWebhookPayload) throws ApiException {
		try {
			OmieClienteService.getInstance().processar(clienteWebhookPayload);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}
}

