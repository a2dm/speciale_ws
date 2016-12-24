package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;

public class ProdutoCliente {

	private BigInteger idProduto;

	private String desProduto;
	
	private String flgFavorito;
	
	private Integer value;
	
	private Integer qtdLoteMinimo;
	
	private Integer qtdMultiplo;

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

	public String getFlgFavorito() {
		return flgFavorito;
	}

	public void setFlgFavorito(String flgFavorito) {
		this.flgFavorito = flgFavorito;
	}

	public Integer getQtdLoteMinimo() {
		return qtdLoteMinimo;
	}

	public void setQtdLoteMinimo(Integer qtdLoteMinimo) {
		this.qtdLoteMinimo = qtdLoteMinimo;
	}

	public Integer getQtdMultiplo() {
		return qtdMultiplo;
	}

	public void setQtdMultiplo(Integer qtdMultiplo) {
		this.qtdMultiplo = qtdMultiplo;
	}
}
