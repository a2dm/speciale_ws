package br.com.a2dm.spdmws.ws;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.a2dm.spdmws.dao.impl.PedidoDaoImpl;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;
import br.com.a2dm.spdmws.entity.ProdutoParam;

@Path("/PedidoWS")
public class PedidoWS
{
	@POST
	@Path("/getPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedido(@FormParam("dataPedido") String dataPedido, @FormParam("idCliente") Long idCliente)
	{
		PedidoCompleto pedidoCompleto = null;
		
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().getPedido(dataPedido, idCliente);
		}
		catch (Exception ex)
		{
			
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/getListaProdutoByCliente")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProdutoCliente> getListaProdutoByCliente(@FormParam("idCliente") Long idCliente)
	{
		List<ProdutoCliente> listaProdutoCliente = null;
		
		try
		{
			listaProdutoCliente = PedidoDaoImpl.getInstancia().getListaProdutoByCliente(idCliente);
		}
		catch (Exception ex)
		{
			
		}
		return listaProdutoCliente;
	}
	
	@POST
	@Path("/cadastrar")	
	@Produces(MediaType.APPLICATION_JSON)
	public Long cadastrar(@FormParam("idCliente") Long idCliente,
						  @FormParam("idUsuario") Long idUsuario, 
                          @FormParam("data") Date data, 
                          @FormParam("observacao") String observacao,
                          @FormParam("produtosAdicionadosJson") String produtosAdicionadosJson) throws JsonParseException, JsonMappingException, IOException
	{
		Long idPedido = null;
		
		ObjectMapper mapper = new ObjectMapper();
		List<ProdutoParam> listaProdutosAdicionados = mapper.readValue(produtosAdicionadosJson, mapper.getTypeFactory().constructCollectionType(List.class, ProdutoParam.class));
		
		try
		{
			idPedido = PedidoDaoImpl.getInstancia().processCadastrar(idCliente, idUsuario, data, observacao, listaProdutosAdicionados);
		}
		catch (Exception ex)
		{
			
		}
		return idPedido;
	}
	
	@POST
	@Path("/alterar")	
	@Produces(MediaType.APPLICATION_JSON)
	public Long alterar(@FormParam("idCliente") Long idCliente,
						@FormParam("idUsuario") Long idUsuario, 
                        @FormParam("data") Date data, 
                        @FormParam("observacao") String observacao,
                        @FormParam("produtosAdicionadosJson") String produtosAdicionadosJson) throws JsonParseException, JsonMappingException, IOException
	{
		Long idPedido = null;
		
		ObjectMapper mapper = new ObjectMapper();
		List<ProdutoParam> listaProdutosAdicionados = mapper.readValue(produtosAdicionadosJson, mapper.getTypeFactory().constructCollectionType(List.class, ProdutoParam.class));
		
		try
		{
			idPedido = PedidoDaoImpl.getInstancia().processAlterar(idCliente, idUsuario, data, observacao, listaProdutosAdicionados);
		}
		catch (Exception ex)
		{
			
		}
		return idPedido;
	}
}