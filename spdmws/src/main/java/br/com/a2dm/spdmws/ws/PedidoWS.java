package br.com.a2dm.spdmws.ws;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.time.DateUtils;

import br.com.a2dm.spdm.entity.Pedido;
import br.com.a2dm.spdm.entity.Produto;
import br.com.a2dm.spdm.service.PedidoService;
import br.com.a2dm.spdmws.builders.PedidoBuilder;
import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.dto.ProdutoDTO;
import br.com.a2dm.spdmws.exception.ApiException;
import br.com.a2dm.spdmws.exception.ExceptionUtils;

@Path("/pedidos")
public class PedidoWS {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoDTO getPedido(@QueryParam("idCliente") BigInteger idCliente,
			@QueryParam("dataPedido") String dataPedido) throws ApiException {
		try {

			Date dataPedidoReferencia = DateUtils.parseDate(dataPedido, new String[] { "yyyy-MM-dd" });

			Pedido pedido = new Pedido();
			pedido.setIdCliente(idCliente);
			pedido.setDatPedido(dataPedidoReferencia);
			pedido.setFlgAtivo("S");
			pedido = PedidoService.getInstancia().get(pedido, 1);

			if (pedido == null) {
				throw new ApiException(404, "Nenhum pedido encontrado com esses parametros");
			}

			return PedidoBuilder.buildPedidoDTOCompleto(pedido);
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

			Pedido pedido = new Pedido();
			pedido.setIdCliente(pedidoDTO.getIdCliente());
			pedido.setObsPedido(pedidoDTO.getObservacao());
			pedido.setDatPedido(pedidoDTO.getDataPedido());
			pedido.setFlgAtivo("S");
			pedido.setPlataforma(PedidoService.PLATAFORMA_APP);

			List<ProdutoDTO> produtos = pedidoDTO.getProdutos();

			if (produtos != null && !produtos.isEmpty()) {

				pedido.setListaProduto(new ArrayList<>());

				for (ProdutoDTO produtoDTO : produtos) {
					Produto produto = new Produto();
					produto.setIdProduto(produtoDTO.getIdProduto());
					produto.setQtdSolicitada(produtoDTO.getQtdSolicitada());
					pedido.getListaProduto().add(produto);
				}
			}

			pedido.setIdPedido(null);
			pedido.setDatCadastro(new Date());
			pedido.setIdUsuarioCad(pedidoDTO.getIdUsuario());
			Pedido pedidoInserido = PedidoService.getInstancia().inserir(pedido);
			pedidoDTO.setIdPedido(pedidoInserido.getIdPedido());

			return PedidoBuilder.buildPedidoDTOCompleto(pedidoInserido.getIdPedido());

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

			if (idPedido == null || !(idPedido.longValue() > 0)) {
				throw new ApiException(400, "Identificação do pedido é obrigatório");
			}else {
				pedidoDTO.setIdPedido(idPedido);
			}

			Pedido pedido = new Pedido();
			pedido.setIdCliente(pedidoDTO.getIdCliente());
			pedido.setObsPedido(pedidoDTO.getObservacao());
			pedido.setDatPedido(pedidoDTO.getDataPedido());
			pedido.setFlgAtivo("S");
			pedido.setPlataforma(PedidoService.PLATAFORMA_APP);

			List<ProdutoDTO> produtos = pedidoDTO.getProdutos();

			if (produtos != null && !produtos.isEmpty()) {

				pedido.setListaProduto(new ArrayList<>());

				for (ProdutoDTO produtoDTO : produtos) {
					Produto produto = new Produto();
					produto.setIdProduto(produtoDTO.getIdProduto());
					produto.setQtdSolicitada(produtoDTO.getQtdSolicitada());
					produto.setFlgAtivo(produtoDTO.getFlgAtivo());
					pedido.getListaProduto().add(produto);
				}
			}

			pedido.setIdPedido(pedidoDTO.getIdPedido());
			pedido.setDatAlteracao(new Date());
			pedido.setIdUsuarioAlt(pedidoDTO.getIdUsuario());
			Pedido pedidoAlterado = PedidoService.getInstancia().alterar(pedido);
			pedidoDTO.setIdPedido(pedidoAlterado.getIdPedido());

			return PedidoBuilder.buildPedidoDTOCompleto(pedidoDTO.getIdPedido());

		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@PUT
	@Path("/{id}/inativar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PedidoDTO inativarPedido(@PathParam("id") BigInteger id, PedidoDTO pedidoDTO) throws ApiException {
		try {
			Pedido pedido = new Pedido();
			pedido.setIdPedido(id);
			pedido.setIdUsuarioAlt(pedidoDTO.getIdUsuario());
			pedido.setIdCliente(pedidoDTO.getIdCliente());

			pedido = PedidoService.getInstancia().inativar(pedido);

			return pedidoDTO;
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}
}