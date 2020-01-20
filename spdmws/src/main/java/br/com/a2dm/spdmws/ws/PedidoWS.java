package br.com.a2dm.spdmws.ws;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.spdm.entity.ClienteProduto;
import br.com.a2dm.spdm.entity.Pedido;
import br.com.a2dm.spdm.service.ClienteProdutoService;
import br.com.a2dm.spdm.service.PedidoService;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;

@Path("/PedidoWS")
public class PedidoWS
{

	@POST
	@Path("/getPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedido(@FormParam("idCliente") BigInteger idCliente, @FormParam("dataPedido") Date dataPedido)
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdCliente(idCliente);
			pedido.setDataPedido(dataPedido);
			pedido = PedidoService.getInstancia().get(pedido, PedidoService.JOIN_PEDIDO_PRODUTO);
			
			pedidoCompleto.setPedido(pedido);
			pedidoCompleto.getPedido().setObservacao(pedido.getObservacao());
			pedidoCompleto.getPedido().setDataPedido(pedido.getDatPedido());
			pedidoCompleto.setListaPedidoProduto(pedido.getListaPedidoProduto());
		}
		catch (Exception ex)
		{
			
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/getPedidoById")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedidoById(@FormParam("idPedido") BigInteger idPedido)
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);
			pedido = PedidoService.getInstancia().get(pedido, PedidoService.JOIN_PEDIDO_PRODUTO);
			
			pedidoCompleto.setPedido(pedido);
			pedidoCompleto.getPedido().setObservacao(pedido.getObservacao());
			pedidoCompleto.getPedido().setDataPedido(pedido.getDatPedido());
			pedidoCompleto.setListaPedidoProduto(pedido.getListaPedidoProduto());
		}
		catch (Exception ex)
		{
			
		}
		return pedidoCompleto;
	}
	
	@POST
	@Path("/getProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoCliente getProduto(@FormParam("idProduto") BigInteger idProduto, @FormParam("idCliente") BigInteger idCliente)
	{
		ProdutoCliente produtoCliente = new ProdutoCliente();
		
		try
		{
			ClienteProduto clienteProduto = new ClienteProduto();
			clienteProduto.setIdCliente(idCliente);
			clienteProduto.setIdProduto(idProduto);
			
			clienteProduto = ClienteProdutoService.getInstancia().get(clienteProduto, ClienteProdutoService.JOIN_PRODUTO);
			
			produtoCliente.setIdProduto(clienteProduto.getProduto().getIdProduto());
			produtoCliente.setDesProduto(clienteProduto.getProduto().getDesProduto());
			produtoCliente.setQtdLoteMinimo(clienteProduto.getProduto().getQtdLoteMinimo());
			produtoCliente.setQtdMultiplo(clienteProduto.getProduto().getQtdMultiplo());
			produtoCliente.setFlgFavorito(clienteProduto.getFlgfavorito());
		}
		catch (Exception ex)
		{
			
		}
		return produtoCliente;
	}
	
	@POST
	@Path("/getListaProdutoByCliente")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProdutoCliente> getListaProdutoByCliente(@FormParam("idCliente") BigInteger idCliente)
	{
		List<ProdutoCliente> listaProdutoCliente = new ArrayList<>();
		
		try
		{
			ClienteProduto clienteProduto = new ClienteProduto();
			clienteProduto.setIdCliente(idCliente);
			
			List<ClienteProduto> list = ClienteProdutoService.getInstancia().pesquisar(clienteProduto, ClienteProdutoService.JOIN_PRODUTO);
			
			if (list != null && list.size() > 0) {
				for (ClienteProduto element : list) {
					ProdutoCliente produtoCliente = new ProdutoCliente();
					produtoCliente.setIdProduto(element.getProduto().getIdProduto());
					produtoCliente.setDesProduto(element.getProduto().getDesProduto());
					produtoCliente.setQtdLoteMinimo(element.getProduto().getQtdLoteMinimo());
					produtoCliente.setQtdMultiplo(element.getProduto().getQtdMultiplo());
					produtoCliente.setFlgFavorito(element.getFlgfavorito());
					
					listaProdutoCliente.add(produtoCliente);
				}
			}
		}
		catch (Exception ex)
		{
			
		}
		return listaProdutoCliente;
	}
	
	@POST
	@Path("/preparaAlterarUltimoPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto preparaAlterarUltimoPedido(@FormParam("idCliente") BigInteger idCliente) throws Exception
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdCliente(idCliente);
			
			List<Pedido> pedidos = PedidoService.getInstancia().pesquisar(pedido, PedidoService.JOIN_PEDIDO_PRODUTO);
			
			if (pedidos != null && pedidos.size() > 0) {
				Pedido ultimoPedido = pedidos.get(pedidos.size()-1);
				pedidoCompleto.setPedido(ultimoPedido);
				pedidoCompleto.getPedido().setObservacao(ultimoPedido.getObservacao());
				pedidoCompleto.getPedido().setDataPedido(ultimoPedido.getDatPedido());
				pedidoCompleto.setListaPedidoProduto(ultimoPedido.getListaPedidoProduto());
			}
			
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
	public PedidoCompleto inativarUltimoPedido(@FormParam("idUsuario") BigInteger idUsuario, 
			                         		  @FormParam("idCliente") BigInteger idCliente) throws Exception
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdCliente(idCliente);
			
			List<Pedido> pedidos = PedidoService.getInstancia().pesquisar(pedido, 0);
			
			if (pedidos != null && pedidos.size() > 0) {
				Pedido ultimoPedido = pedidos.get(pedidos.size()-1);
				
				Pedido pedidoInativar = new Pedido();
				pedido.setIdPedido(ultimoPedido.getIdPedido());
				pedido.setIdUsuarioAlt(idUsuario);
				
				PedidoService.getInstancia().inativar(pedidoInativar);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
//	@POST
//	@Path("/cadastrar")	
//	@Produces(MediaType.APPLICATION_JSON)
//	public BigInteger cadastrar(@FormParam("idCliente") BigInteger idCliente,
//						  @FormParam("idUsuario") BigInteger idUsuario,
//						  @FormParam("idPedido") BigInteger idPedido,
//                          @FormParam("data") Date data, 
//                          @FormParam("observacao") String observacao,
//                          @FormParam("produtosAdicionadosJson") String produtosAdicionadosJson) throws JsonParseException, JsonMappingException, IOException
//	{
//		ObjectMapper mapper = new ObjectMapper();
//		List<ProdutoParam> listaProdutosAdicionados = mapper.readValue(produtosAdicionadosJson, mapper.getTypeFactory().constructCollectionType(List.class, ProdutoParam.class));
//		
//		try
//		{
//			Pedido pedido = new Pedido();
//			pedido.setIdCliente(idCliente);
//			pedido.setObsPedido(observacao);
//			
//			if (listaProdutosAdicionados != null 
//					&& listaProdutosAdicionados.size() > 0) {
//				
//				pedido.setListaProduto(new ArrayList<>());
//				
//				for (ProdutoParam element : listaProdutosAdicionados) {
//					Produto produto = new Produto();
//					produto.setIdProduto(element.getIdProduto());
//					produto.setQtdSolicitada(element.getQuantity());
//					
//					pedido.getListaProduto().add(produto);
//				}
//			}
//			
//			if (idPedido != null) {
//				pedido.setIdUsuarioAlt(idUsuario);
//				pedido.setDatAlteracao(data);
//				pedido.setIdPedido(idPedido);
//				
//				Pedido pedidoAlterado = PedidoService.getInstancia().alterar(pedido);
//				
//				idPedido = pedidoAlterado.getIdPedido();
//			} else {
//				pedido.setIdUsuarioCad(idUsuario);
//				pedido.setDatCadastro(data);
//				
//				Pedido pedidoInserido = PedidoService.getInstancia().inserir(pedido);
//				
//				idPedido = pedidoInserido.getIdPedido();
//			}
//		}
//		catch (Exception ex)
//		{
//			
//		}
//		return idPedido;
//	}
	
	@POST
	@Path("/inativar")	
	@Produces(MediaType.APPLICATION_JSON)
	public BigInteger inativar(@FormParam("idPedido") BigInteger idPedido,
						 @FormParam("idUsuario") BigInteger idUsuario) throws IOException
	{
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);
			pedido.setIdUsuarioAlt(idUsuario);
			
			PedidoService.getInstancia().inativar(pedido);
		}
		catch (Exception ex)
		{
			
		}
		return idPedido;
	}
	
	@POST
	@Path("/novoPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto novoPedido() throws Exception
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		try
		{
			pedidoCompleto.setPedido(new Pedido());
			pedidoCompleto.getPedido().setIdPedido(null);
			pedidoCompleto.getPedido().setDataPedido(new Date());
			pedidoCompleto.getPedido().setObservacao("");
			pedidoCompleto.setListaPedidoProduto(new ArrayList<>());
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
	public PedidoCompleto preparaAlterarPedido(@FormParam("idPedido") BigInteger idPedido) throws Exception
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);
			pedido = PedidoService.getInstancia().get(pedido, PedidoService.JOIN_PEDIDO_PRODUTO);
			
			pedidoCompleto.setPedido(pedido);
			pedidoCompleto.getPedido().setObservacao(pedido.getObservacao());
			pedidoCompleto.getPedido().setDataPedido(pedido.getDatPedido());
			pedidoCompleto.setListaPedidoProduto(pedido.getListaPedidoProduto());
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
}