package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;
import java.util.Date;

public class ProdutoParam {

	private Long id;

	private String name;
	
	private String desProduto;

	private BigInteger quantity;
	
	private Integer qtdSolicitada;

	private Long idPedidoProduto;

	private Long idUsuarioCad;

	private Date datCadastro;

	private BigInteger idProduto;
	
	private Boolean flgAtivo;

	private Long idPedido;
	
	private Integer qtdLoteMinimo;
	
	private Integer qtdMultiplo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}

	public Long getIdPedidoProduto() {
		return idPedidoProduto;
	}

	public void setIdPedidoProduto(Long idPedidoProduto) {
		this.idPedidoProduto = idPedidoProduto;
	}

	public Long getIdUsuarioCad() {
		return idUsuarioCad;
	}

	public void setIdUsuarioCad(Long idUsuarioCad) {
		this.idUsuarioCad = idUsuarioCad;
	}

	public Date getDatCadastro() {
		return datCadastro;
	}

	public void setDatCadastro(Date datCadastro) {
		this.datCadastro = datCadastro;
	}

	public Boolean getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

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

	public Integer getQtdSolicitada() {
		return qtdSolicitada;
	}

	public void setQtdSolicitada(Integer qtdSolicitada) {
		this.qtdSolicitada = qtdSolicitada;
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