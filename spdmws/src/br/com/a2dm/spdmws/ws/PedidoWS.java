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
import br.com.a2dm.spdmws.entity.Mensagem;
import br.com.a2dm.spdmws.entity.Pedido;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;
import br.com.a2dm.spdmws.entity.ProdutoParam;

@Path("/PedidoWS")
public class PedidoWS
{
	@POST
	@Path("/getPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedido(@FormParam("idCliente") Long idCliente, @FormParam("dataPedido") Date dataPedido)
	{
		PedidoCompleto pedidoCompleto = null;
		
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().getPedido(idCliente, dataPedido);
		}
		catch (Exception ex)
		{
			
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/getPedidoById")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedidoById(@FormParam("idPedido") Long idPedido)
	{
		PedidoCompleto pedidoCompleto = null;
		
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().getPedidoById(idPedido);
		}
		catch (Exception ex)
		{
			
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/getProduct")	
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoCliente getProduto(@FormParam("idProduto") Long idProduto)
	{
		ProdutoCliente produtoCliente = null;
		
		try
		{
			produtoCliente = PedidoDaoImpl.getInstancia().getProduto(idProduto);
		}
		catch (Exception ex)
		{
			
		}
		return produtoCliente;
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
						  @FormParam("idPedido") Long idPedido,
                          @FormParam("data") Date data, 
                          @FormParam("observacao") String observacao,
                          @FormParam("produtosAdicionadosJson") String produtosAdicionadosJson) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		List<ProdutoParam> listaProdutosAdicionados = mapper.readValue(produtosAdicionadosJson, mapper.getTypeFactory().constructCollectionType(List.class, ProdutoParam.class));
		
		try
		{
			idPedido = PedidoDaoImpl.getInstancia().process(idCliente, idUsuario, idPedido, data, observacao, listaProdutosAdicionados);
		}
		catch (Exception ex)
		{
			
		}
		return idPedido;
	}
	
	@POST
	@Path("/inativar")	
	@Produces(MediaType.APPLICATION_JSON)
	public Long inativar(@FormParam("idPedido") Long idPedido,
						 @FormParam("idUsuario") Long idUsuario) throws JsonParseException, JsonMappingException, IOException
	{
		try
		{
			PedidoDaoImpl.getInstancia().inativar(idPedido, idUsuario);
		}
		catch (Exception ex)
		{
			
		}
		return idPedido;
	}
	
	@POST
	@Path("/preparaAlterarUltimoPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto preparaAlterarUltimoPedido(@FormParam("idCliente") Long idCliente) throws Exception
	{
		PedidoCompleto pedidoCompleto = null;
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().preparaAlterarUltimoPedido(idCliente);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/novoPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto novoPedido() throws Exception
	{
		PedidoCompleto pedidoCompleto = null;
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().novoPedido();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/preparaAlterarPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto preparaAlterarPedido(@FormParam("idPedido") Long idPedido) throws Exception
	{
		PedidoCompleto pedidoCompleto = null;
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().preparaAlterarPedido(idPedido);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/inativarUltimoPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto inativarUltimoPedido(@FormParam("idUsuario") Long idUsuario, 
			                         		  @FormParam("idCliente") Long idCliente) throws Exception
	{
		PedidoCompleto pedidoCompleto = null;
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().inativarUltimoPedido(idUsuario, idCliente);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/inativarPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto inativarPedido(@FormParam("idUsuario") Long idUsuario, 
			                         		  @FormParam("idPedido") Long idPedido) throws Exception
	{
		PedidoCompleto pedidoCompleto = null;
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().inativarPedido(idUsuario, idPedido);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/getUltimoPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido getUltimoPedido(@FormParam("idCliente") Long idCliente) throws Exception
	{
		Pedido pedido = null;
		try
		{
			pedido = PedidoDaoImpl.getInstancia().getUltimoPedido(idCliente);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedido;
	}
	
	@POST
	@Path("/validar")	
	@Produces(MediaType.APPLICATION_JSON)
	public Mensagem validar(@FormParam("idProduto") Long idProduto, 
			              @FormParam("quantidade") Integer quantidade) throws Exception
	{
		Mensagem mensagem = null;
		try
		{
			mensagem = PedidoDaoImpl.getInstancia().validar(idProduto, quantidade);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return mensagem;
	}
	
	@POST
	@Path("/validarData")	
	@Produces(MediaType.APPLICATION_JSON)
	public Mensagem validarData(@FormParam("idCliente") Long idCliente,
								@FormParam("idPedido") Long idPedido,
			                    @FormParam("dataPedido") Date dataPedido) throws Exception
	{
		Mensagem mensagem = null;
		try
		{
			mensagem = PedidoDaoImpl.getInstancia().validarData(idCliente, idPedido, dataPedido);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return mensagem;
	}
	
	@POST
	@Path("/validarInativar")	
	@Produces(MediaType.APPLICATION_JSON)
	public Mensagem validarInativar(@FormParam("idCliente") Long idCliente,
			                    @FormParam("dataPedido") Date dataPedido) throws Exception
	{
		Mensagem mensagem = null;
		try
		{
			mensagem = PedidoDaoImpl.getInstancia().validarInativar(idCliente, dataPedido);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return mensagem;
	}
}