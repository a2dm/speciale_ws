package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;


public class ProdutoPayload {

	private String cfop;
	private String ncm;
	private BigInteger codigo_produto;
	private String descricao;
	private BigInteger quantidade;
	private String tipo_desconto;
	private String unidade;
	private String valor_desconto;
	private Double valor_unitario;

	public ProdutoPayload(String cfop, String ncm, BigInteger codigo_produto, String descricao, BigInteger quantidade,
			String tipo_desconto, String unidade, String valor_desconto, Double valor_unitario) {
		super();
		this.cfop = cfop;
		this.ncm = ncm;
		this.codigo_produto = codigo_produto;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tipo_desconto = tipo_desconto;
		this.unidade = unidade;
		this.valor_desconto = valor_desconto;
		this.valor_unitario = valor_unitario;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public BigInteger getCodigo_produto() {
		return codigo_produto;
	}

	public void setCodigo_produto(BigInteger codigo_produto) {
		this.codigo_produto = codigo_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo_desconto() {
		return tipo_desconto;
	}

	public void setTipo_desconto(String tipo_desconto) {
		this.tipo_desconto = tipo_desconto;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(String valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
}
