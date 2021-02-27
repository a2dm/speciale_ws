package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class ConsultarPayload {

	private BigInteger numero_pedido;

	public ConsultarPayload(BigInteger numero_pedido) {
		super();
		this.numero_pedido = numero_pedido;
	}

	public BigInteger getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(BigInteger numero_pedido) {
		this.numero_pedido = numero_pedido;
	}
}
