package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;

public class PedidoProduto
{
	private BigInteger idPedidoProduto;
	
	private BigInteger idPedido;
	
	private String desProduto;
	
	private Integer qtdSolicitada;

	public BigInteger getIdPedidoProduto() {
		return idPedidoProduto;
	}

	public void setIdPedidoProduto(BigInteger idPedidoProduto) {
		this.idPedidoProduto = idPedidoProduto;
	}

	public BigInteger getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(BigInteger idPedido) {
		this.idPedido = idPedido;
	}

	public String getDesProduto() {
		return desProduto;
	}

	public void setDesProduto(String desProduto) {
		this.desProduto = desProduto;
	}

	public Integer getQtdSolicitada() {
		return qtdSolicitada;
	}

	public void setQtdSolicitada(Integer qtdSolicitada) {
		this.qtdSolicitada = qtdSolicitada;
	}
}
