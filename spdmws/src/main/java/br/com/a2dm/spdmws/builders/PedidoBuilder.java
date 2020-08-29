package br.com.a2dm.spdmws.builders;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.com.a2dm.spdm.entity.Pedido;
import br.com.a2dm.spdm.entity.PedidoProduto;
import br.com.a2dm.spdm.service.PedidoProdutoService;
import br.com.a2dm.spdm.service.PedidoService;
import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.dto.ProdutoDTO;

public class PedidoBuilder {

	public static PedidoDTO buildPedidoDTOCompleto(BigInteger idPedido) throws Exception {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(idPedido);
		pedido = PedidoService.getInstancia().get(pedido, 1);
		return buildPedidoDTOCompleto(pedido);

	}
	
	public static PedidoDTO buildPedidoDTOCompleto(Pedido pedido) throws Exception {

		PedidoDTO pedidoDTO = PedidoBuilder.buildPedidoDTO(pedido);

		PedidoProduto pedidoProdutoPesquisa = new PedidoProduto();
		pedidoProdutoPesquisa.setIdPedido(pedido.getIdPedido());
		pedidoProdutoPesquisa.setFlgAtivo("S");

		List<PedidoProduto> listaPedidoProduto = PedidoProdutoService.getInstancia().pesquisar(pedidoProdutoPesquisa,
				PedidoProdutoService.JOIN_PRODUTO);

		if (listaPedidoProduto != null && !listaPedidoProduto.isEmpty()) {
			for (PedidoProduto pedidoProduto : listaPedidoProduto) {
				ProdutoDTO produtoDTO = PedidoBuilder.buildProdutoDTO(pedidoProduto);
				pedidoDTO.getProdutos().add(produtoDTO);
			}
		}

		return pedidoDTO;

	}

	public static PedidoDTO buildPedidoDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDataPedido(pedido.getDatPedido());
		pedidoDTO.setObservacao(pedido.getObsPedido());
		pedidoDTO.setIdCliente(pedido.getIdCliente());
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setIdOpcaoEntrega(pedido.getIdOpcaoEntrega());
		pedidoDTO.setIdUsuario(pedido.getIdUsuarioCad());
		pedidoDTO.setUsuarioCadastro(UsuarioBuilder.buildUsuarioDTO(pedido.getUsuarioCad()));
		pedidoDTO.setFlgAtivo(pedido.getFlgAtivo());
		pedidoDTO.setProdutos(new ArrayList<>());
		return pedidoDTO;
	}

	public static ProdutoDTO buildProdutoDTO(PedidoProduto pedidoProduto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdPedidoProduto(pedidoProduto.getIdPedidoProduto());
		produtoDTO.setDesProduto(pedidoProduto.getProduto().getDesProduto());
		produtoDTO.setIdProduto(pedidoProduto.getProduto().getIdProduto());
		produtoDTO.setQtdMultiplo(pedidoProduto.getProduto().getQtdMultiplo());
		produtoDTO.setQtdLoteMinimo(pedidoProduto.getProduto().getQtdLoteMinimo());
		produtoDTO.setQtdSolicitada(pedidoProduto.getQtdSolicitada());
//		produtoDTO.setFlgAtivo(pedidoProduto.getFlgAtivo());
		return produtoDTO;
	}
}
