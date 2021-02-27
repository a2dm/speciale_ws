package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class IdePayload {

	private BigInteger codigo_item_integracao;
	private String acao_item;

	public IdePayload(BigInteger codigo_item_integracao, String acao_item) {
		super();
		this.codigo_item_integracao = codigo_item_integracao;
		this.acao_item = acao_item;
	}

	public BigInteger getCodigo_item_integracao() {
		return codigo_item_integracao;
	}

	public void setCodigo_item_integracao(BigInteger codigo_item_integracao) {
		this.codigo_item_integracao = codigo_item_integracao;
	}

	public String getAcao_item() {
		return acao_item;
	}

	public void setAcao_item(String acao_item) {
		this.acao_item = acao_item;
	}
}
