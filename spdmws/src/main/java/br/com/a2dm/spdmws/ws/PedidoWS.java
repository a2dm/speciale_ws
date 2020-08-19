package br.com.a2dm.spdmws.ws;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.a2dm.spdm.entity.ClienteProduto;
import br.com.a2dm.spdm.entity.OpcaoEntrega;
import br.com.a2dm.spdm.entity.Pedido;
import br.com.a2dm.spdm.entity.PedidoProduto;
import br.com.a2dm.spdm.entity.Produto;
import br.com.a2dm.spdm.exception.ServiceException;
import br.com.a2dm.spdm.service.ClienteProdutoService;
import br.com.a2dm.spdm.service.OpcaoEntregaService;
import br.com.a2dm.spdm.service.PedidoProdutoService;
import br.com.a2dm.spdm.service.PedidoService;
import br.com.a2dm.spdm.service.ProdutoService;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;
import br.com.a2dm.spdmws.utils.JsonUtils;

@Path("/PedidoWS")
public class PedidoWS
{

	@POST
	@Path("/getPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedido(@FormParam("idCliente") BigInteger idCliente, @FormParam("dataPedido") Date dataPedido) throws ServiceException 
	{
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdCliente(idCliente);
			pedido.setDatPedido(dataPedido);
			pedido.setFlgAtivo("S");
			pedido = PedidoService.getInstancia().get(pedido, 0);
			
			pedidoCompleto.setPedido(pedido);
			pedidoCompleto.getPedido().setObservacao(pedido.getObservacao());
			
			PedidoProduto pedidoProduto = new PedidoProduto();
			pedidoProduto.setIdPedido(pedido.getIdPedido());
			pedidoProduto.setFlgAtivo("S");
			
			List<PedidoProduto> listaPedidoProduto = PedidoProdutoService.getInstancia().pesquisar(pedidoProduto, PedidoProdutoService.JOIN_PRODUTO);
			pedidoCompleto.setListaPedidoProduto(listaPedidoProduto);
			
			return JsonUtils.serializeInstance(pedidoCompleto);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@POST
	@Path("/getProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoCliente getProduto(@FormParam("idProduto") BigInteger idProduto, @FormParam("idCliente") BigInteger idCliente) throws ServiceException
	{
		ProdutoCliente produtoCliente = new ProdutoCliente();
		
		try
		{
			ClienteProduto clienteProduto = new ClienteProduto();
			clienteProduto.setIdCliente(idCliente);
			clienteProduto.setIdProduto(idProduto);
			clienteProduto.setFlgAtivo("S");
			
			clienteProduto = ClienteProdutoService.getInstancia().get(clienteProduto, ClienteProdutoService.JOIN_PRODUTO);
			
			produtoCliente.setIdProduto(clienteProduto.getProduto().getIdProduto());
			produtoCliente.setDesProduto(clienteProduto.getProduto().getDesProduto());
			produtoCliente.setQtdLoteMinimo(clienteProduto.getProduto().getQtdLoteMinimo());
			produtoCliente.setQtdMultiplo(clienteProduto.getProduto().getQtdMultiplo());
			produtoCliente.setFlgFavorito(clienteProduto.getFlgfavorito());
			
			return JsonUtils.serializeInstance(produtoCliente);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@POST
	@Path("/getListaProdutoByCliente")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProdutoCliente> getListaProdutoByCliente(@FormParam("idCliente") BigInteger idCliente) throws ServiceException
	{
		List<ProdutoCliente> listaProdutoCliente = new ArrayList<>();
		
		try
		{
			Produto produto = new Produto();
			produto.setFlgAtivo("S");
			produto.setFiltroMap(new HashMap<String, Object>());
			produto.getFiltroMap().put("flgAtivoClienteProduto", "S");
			produto.getFiltroMap().put("idCliente", idCliente);
			
			List<Produto> list = ProdutoService.getInstancia().pesquisar(produto, ProdutoService.JOIN_CLIENTE_PRODUTO);
			
			if (list != null && list.size() > 0) {
				for (Produto element : list) {
					ProdutoCliente produtoCliente = new ProdutoCliente();
					produtoCliente.setIdProduto(element.getIdProduto());
					produtoCliente.setDesProduto(element.getDesProduto());
					produtoCliente.setQtdLoteMinimo(element.getQtdLoteMinimo());
					produtoCliente.setQtdMultiplo(element.getQtdMultiplo());
					
					listaProdutoCliente.add(produtoCliente);
				}
			}
			return JsonUtils.serializeInstance(listaProdutoCliente);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@POST
	@Path("/cadastrar")	
	@Produces(MediaType.APPLICATION_JSON)
	public BigInteger cadastrar(@FormParam("idCliente") BigInteger idCliente,
						  @FormParam("idUsuario") BigInteger idUsuario,
						  @FormParam("idPedido") BigInteger idPedido,
                          @FormParam("data") Date data, 
                          @FormParam("observacao") String observacao,
                          @FormParam("produtosAdicionadosJson") String produtosAdicionadosJson,
                          @FormParam("produtosRemovidosJson") String produtosRemovidosJson) throws JsonParseException, JsonMappingException, IOException, JSONException, ServiceException
	{
		JSONObject obj = new JSONObject(produtosAdicionadosJson);
		JSONArray listaProdutosAdicionados = obj.getJSONArray("data");
		
		JSONObject objRemovido = new JSONObject(produtosRemovidosJson);
		JSONArray listaProdutosRemovidos= objRemovido.getJSONArray("data");
		
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdCliente(idCliente);
			pedido.setObsPedido(observacao);
			pedido.setDatPedido(data);
			pedido.setFlgAtivo("S");
			pedido.setPlataforma(PedidoService.PLATAFORMA_APP);
			
			if (listaProdutosAdicionados != null 
					&& listaProdutosAdicionados.length() > 0) {
				
				pedido.setListaProduto(new ArrayList<>());
				
				for (int i = 0; i < listaProdutosAdicionados.length(); i++) {
					JSONObject o = listaProdutosAdicionados.getJSONObject(i);
					
					Produto produto = new Produto();
					produto.setIdProduto(new BigInteger(o.getString("idProduto")));
					produto.setQtdSolicitada(new BigInteger(o.getString("quantity")));
					
					if (!o.isNull("flgAtivo")) {
						produto.setFlgAtivo(o.getString("flgAtivo"));
					}
					
					pedido.getListaProduto().add(produto);
				}
				
				for (int i = 0; i < listaProdutosRemovidos.length(); i++) {
					JSONObject o = listaProdutosRemovidos.getJSONObject(i);
					
					Produto produto = new Produto();
					produto.setIdProduto(new BigInteger(o.getString("idProduto")));
					produto.setQtdSolicitada(new BigInteger(o.getString("quantity")));
					
					if (!o.isNull("flgAtivo")) {
						produto.setFlgAtivo(o.getString("flgAtivo"));
					}
					
					pedido.getListaProduto().add(produto);
				}
			}
			
			if (idPedido != null && idPedido.longValue() > 0) {
				pedido.setIdPedido(idPedido);
				pedido.setDatAlteracao(new Date());
				pedido.setIdUsuarioAlt(idUsuario);
				
				Pedido pedidoAlterado = PedidoService.getInstancia().alterar(pedido);
				
				idPedido = pedidoAlterado.getIdPedido();
			} else {
				pedido.setIdPedido(null);
				pedido.setDatCadastro(new Date());
				pedido.setIdUsuarioCad(idUsuario);
				
				Pedido pedidoInserido = PedidoService.getInstancia().inserir(pedido);
				
				idPedido = pedidoInserido.getIdPedido();
			}
		}
		catch (Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		return idPedido;
	}
	
	@POST
	@Path("/inativarPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido inativarPedido(@FormParam("idPedido") BigInteger idPedido, 
			                         @FormParam("idUsuario") BigInteger idUsuario,
			                         @FormParam("idCliente") BigInteger idCliente) throws ServiceException
	{
		try
		{
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);
			pedido.setIdUsuarioAlt(idUsuario);
			pedido.setIdCliente(idCliente);
			
			pedido = PedidoService.getInstancia().inativar(pedido);
			
			return JsonUtils.serializeInstance(pedido);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
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
	@Path("/listarOpcaoEntrega")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OpcaoEntrega> listarOpcaoFrete() throws ServiceException
	{
		try
		{
			List<OpcaoEntrega> listOpcaoEntrega = OpcaoEntregaService.getInstancia().pesquisar(new OpcaoEntrega(), 0); 
			return JsonUtils.serializeInstance(listOpcaoEntrega);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@POST
	@Path("/buscarValorFrete")
	@Produces(MediaType.APPLICATION_JSON)
	public Double buscarInformacoesOpcaoEntrega(@FormParam("idCliente") BigInteger idCliente, @FormParam("idOpcaoEntrega") BigInteger idOpcaoEntrega) throws ServiceException
	{
		try
		{
			String valorFreteFormatado = PedidoService.getInstancia().buscarInformacoesOpcaoEntrega(idCliente, idOpcaoEntrega); 
			return new Double(valorFreteFormatado.toString().replace(".", "").replace(",", "."));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}