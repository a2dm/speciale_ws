package br.com.a2dm.spdmws.ws;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.brcmn.dto.ProdutoDTO;
import br.com.a2dm.spdm.entity.ClienteProduto;
import br.com.a2dm.spdm.exception.ApiException;
import br.com.a2dm.spdm.exception.ExceptionUtils;
import br.com.a2dm.spdm.omie.service.OmieProdutoService;
import br.com.a2dm.spdm.service.ClienteProdutoService;

@Path("/produtos")
public class ProdutosWS {

	@GET
	@Path("/{idProduto}/cliente/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoDTO getProduto(@PathParam("idProduto") BigInteger idProduto,
			@PathParam("idCliente") BigInteger idCliente) throws ApiException {
		try {
			ClienteProduto clienteProduto = new ClienteProduto();
			clienteProduto.setIdCliente(idCliente);
			clienteProduto.setIdProduto(idProduto);
			clienteProduto.setFlgAtivo("S");

			clienteProduto = ClienteProdutoService.getInstancia().get(clienteProduto,
					ClienteProdutoService.JOIN_PRODUTO);
			
			if(clienteProduto == null) {
				throw new ApiException(404, "Produto não encontrado para cliente");
			}

			ProdutoDTO produtoCliente = new ProdutoDTO();
			produtoCliente.setIdProduto(clienteProduto.getProduto().getIdProduto());
			produtoCliente.setDesProduto(clienteProduto.getProduto().getDesProduto());
			produtoCliente.setQtdLoteMinimo(clienteProduto.getProduto().getQtdLoteMinimo());
			produtoCliente.setQtdMultiplo(clienteProduto.getProduto().getQtdMultiplo());
			produtoCliente.setFlgAtivo("S");
			return produtoCliente;
		
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@GET
	@Path("/clientes/{idCliente}")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProdutoDTO> getListaProdutoByCliente(@PathParam("idCliente") BigInteger idCliente) throws ApiException
	{
		try
		{
//			List<ProdutoDTO> listaProdutoCliente = new ArrayList<ProdutoDTO>();
//			Produto produto = new Produto();
//			produto.setFlgAtivo("S");
//			produto.setFiltroMap(new HashMap<String, Object>());
//			produto.getFiltroMap().put("flgAtivoClienteProduto", "S");
//			produto.getFiltroMap().put("idCliente", idCliente);
//			
//			List<Produto> list = ProdutoService.getInstancia().pesquisar(produto, ProdutoService.JOIN_CLIENTE_PRODUTO);
//			
//			if (list != null && list.size() > 0) {
//				for (Produto element : list) {
//					ProdutoDTO produtoCliente = new ProdutoDTO();
//					produtoCliente.setIdProduto(element.getIdProduto());
//					produtoCliente.setDesProduto(element.getDesProduto());
//					produtoCliente.setQtdLoteMinimo(element.getQtdLoteMinimo());
//					produtoCliente.setQtdMultiplo(element.getQtdMultiplo());
//					produtoCliente.setFlgAtivo("S");
//					listaProdutoCliente.add(produtoCliente);
//				}
//			}
//			return listaProdutoCliente;
			return OmieProdutoService.getInstance().listarProdutosPorCliente(idCliente);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

}