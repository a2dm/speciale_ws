package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class CabecalhoPayload {

	private long codigo_pedido_integracao;
	private BigInteger codigo_cliente;
	private String data_previsao;
	private String etapa;
	private String codigo_parcela;
	private Integer quantidade_itens;

	public CabecalhoPayload(long codigo_pedido_integracao, BigInteger codigo_cliente, String data_previsao,
			String etapa, String codigo_parcela, Integer quantidade_itens) {
		super();
		this.codigo_pedido_integracao = codigo_pedido_integracao;
		this.codigo_cliente = codigo_cliente;
		this.data_previsao = data_previsao;
		this.etapa = etapa;
		this.codigo_parcela = codigo_parcela;
		this.quantidade_itens = quantidade_itens;
	}

	public long getCodigo_pedido_integracao() {
		return codigo_pedido_integracao;
	}

	public void setCodigo_pedido_integracao(long codigo_pedido_integracao) {
		this.codigo_pedido_integracao = codigo_pedido_integracao;
	}

	public BigInteger getCodigo_cliente() {
		return codigo_cliente;
	}

	public void setCodigo_cliente(BigInteger codigo_cliente) {
		this.codigo_cliente = codigo_cliente;
	}

	public String getData_previsao() {
		return data_previsao;
	}

	public void setData_previsao(String data_previsao) {
		this.data_previsao = data_previsao;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getCodigo_parcela() {
		return codigo_parcela;
	}

	public void setCodigo_parcela(String codigo_parcela) {
		this.codigo_parcela = codigo_parcela;
	}

	public Integer getQuantidade_itens() {
		return quantidade_itens;
	}

	public void setQuantidade_itens(Integer quantidade_itens) {
		this.quantidade_itens = quantidade_itens;
	}
}
