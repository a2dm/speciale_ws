package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;
import java.util.Date;

public class PedidoProduto {

	private BigInteger idPedidoProduto;

	private BigInteger idProduto;

	private BigInteger idPedido;

	private BigInteger idUsuarioCad;

	private String desProduto;
	
	private String name;

	private Integer qtdSolicitada;
	
	private Integer qtdLoteMinimo;
	
	private Integer qtdMultiplo;

	private Date datCadastro;

	private String flgAtivo;

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

	public Date getDatCadastro() {
		return datCadastro;
	}

	public void setDatCadastro(Date datCadastro) {
		this.datCadastro = datCadastro;
	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public BigInteger getIdUsuarioCad() {
		return idUsuarioCad;
	}

	public void setIdUsuarioCad(BigInteger idUsuarioCad) {
		this.idUsuarioCad = idUsuarioCad;
	}

	public BigInteger getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(BigInteger idProduto) {
		this.idProduto = idProduto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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