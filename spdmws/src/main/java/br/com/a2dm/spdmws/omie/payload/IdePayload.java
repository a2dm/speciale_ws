package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class IdePayload {

	private BigInteger codigo_item_integracao;

	public IdePayload(BigInteger codigo_item_integracao) {
		super();
		this.codigo_item_integracao = codigo_item_integracao;
	}

	public BigInteger getCodigo_item_integracao() {
		return codigo_item_integracao;
	}

	public void setCodigo_item_integracao(BigInteger codigo_item_integracao) {
		this.codigo_item_integracao = codigo_item_integracao;
	}
}
