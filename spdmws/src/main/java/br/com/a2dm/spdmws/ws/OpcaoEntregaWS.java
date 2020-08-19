package br.com.a2dm.spdmws.ws;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.spdm.entity.OpcaoEntrega;
import br.com.a2dm.spdm.service.OpcaoEntregaService;
import br.com.a2dm.spdm.service.PedidoService;
import br.com.a2dm.spdmws.exception.ApiException;
import br.com.a2dm.spdmws.exception.ExceptionUtils;

@Path("/opcoesEntregas")
public class OpcaoEntregaWS {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<OpcaoEntrega> listarOpcaoFrete() throws ApiException 
	{
		try
		{
			return OpcaoEntregaService.getInstancia().pesquisar(new OpcaoEntrega(), 0); 
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}
	
	@GET
	@Path("{idOpcaoEntrega}/buscarValorFrete/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Double buscarInformacoesOpcaoEntrega(@PathParam("idOpcaoEntrega") BigInteger idOpcaoEntrega, @PathParam("idCliente") BigInteger idCliente) throws ApiException
	{
		try
		{
			String valorFreteFormatado = PedidoService.getInstancia().buscarInformacoesOpcaoEntrega(idCliente, idOpcaoEntrega); 
			return new Double(valorFreteFormatado.toString().replace(".", "").replace(",", "."));
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}
}