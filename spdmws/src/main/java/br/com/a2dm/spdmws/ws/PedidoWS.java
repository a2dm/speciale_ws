package br.com.a2dm.spdmws.ws;

import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.brcmn.dto.PedidoDTO;
import br.com.a2dm.spdm.exception.ApiException;
import br.com.a2dm.spdm.exception.ExceptionUtils;
import br.com.a2dm.spdm.omie.service.OmiePedidoService;

@Path("/pedidos")
public class PedidoWS {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoDTO getPedido(@QueryParam("idCliente") BigInteger idCliente,
							   @QueryParam("idPedido") BigInteger idPedido,
							   @QueryParam("dataPedido") String dataPedido) throws ApiException {
		try {
			return OmiePedidoService.getInstance().pesquisarPedido(idCliente, idPedido, dataPedido);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO) throws ApiException {
		try {
			return OmiePedidoService.getInstance().cadastrarPedido(pedidoDTO);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@PUT
	@Path("/{idPedido}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PedidoDTO atualizarPedido(@PathParam("idPedido") BigInteger idPedido, PedidoDTO pedidoDTO) throws ApiException {
		try {
			return OmiePedidoService.getInstance().alterarPedido(pedidoDTO);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@PUT
	@Path("/{id}/inativar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void inativarPedido(@PathParam("id") BigInteger id, PedidoDTO pedidoDTO) throws ApiException {
		try {
			OmiePedidoService.getInstance().inativarPedido(id, pedidoDTO);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}
}

