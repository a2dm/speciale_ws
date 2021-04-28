package br.com.a2dm.spdmws.ws;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.brcmn.dto.ClienteDTO;
import br.com.a2dm.spdm.exception.ApiException;
import br.com.a2dm.spdm.exception.ExceptionUtils;
import br.com.a2dm.spdm.omie.payload.ClienteWebhookPayload;
import br.com.a2dm.spdm.omie.service.OmieClienteService;

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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<BigInteger> alterarClientes(List<String> clientes) throws ApiException {
		try {
			return OmieClienteService.getInstance().alterarClientes(clientes);
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

