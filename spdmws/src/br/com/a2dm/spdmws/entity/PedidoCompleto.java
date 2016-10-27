package br.com.a2dm.spdmws.entity;

import java.util.List;

public class PedidoCompleto
{	
	private Pedido pedido;
	
	private List<PedidoProduto> listaPedidoProduto;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<PedidoProduto> getListaPedidoProduto() {
		return listaPedidoProduto;
	}

	public void setListaPedidoProduto(List<PedidoProduto> listaPedidoProduto) {
		this.listaPedidoProduto = listaPedidoProduto;
	}
}
