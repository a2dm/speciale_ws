package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;

public class ProdutoCliente {

	private BigInteger idProduto;

	private String desProduto;
	
	private Integer value;

	public BigInteger getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(BigInteger idProduto) {
		this.idProduto = idProduto;
	}

	public String getDesProduto() {
		return desProduto;
	}

	public void setDesProduto(String desProduto) {
		this.desProduto = desProduto;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
