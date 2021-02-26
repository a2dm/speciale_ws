package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class PesquisarPayloadPedido {

	private BigInteger filtrar_por_cliente;
	private BigInteger numero_pedido_de;
	private BigInteger numero_pedido_ate;
	private String filtrar_por_data_de;
	private String filtrar_por_data_ate;

	public PesquisarPayloadPedido(BigInteger filtrar_por_cliente, BigInteger numeroPedido, String dataPedido) {
		super();
		this.filtrar_por_cliente = filtrar_por_cliente;
		this.numero_pedido_de = numeroPedido;
		this.numero_pedido_ate = numeroPedido;
		this.filtrar_por_data_de = dataPedido;
		this.filtrar_por_data_ate = dataPedido;
	}

	public BigInteger getFiltrar_por_cliente() {
		return filtrar_por_cliente;
	}

	public void setFiltrar_por_cliente(BigInteger filtrar_por_cliente) {
		this.filtrar_por_cliente = filtrar_por_cliente;
	}

	public BigInteger getNumero_pedido_de() {
		return numero_pedido_de;
	}

	public void setNumero_pedido_de(BigInteger numero_pedido_de) {
		this.numero_pedido_de = numero_pedido_de;
	}

	public BigInteger getNumero_pedido_ate() {
		return numero_pedido_ate;
	}

	public void setNumero_pedido_ate(BigInteger numero_pedido_ate) {
		this.numero_pedido_ate = numero_pedido_ate;
	}

	public String getFiltrar_por_data_de() {
		return filtrar_por_data_de;
	}

	public void setFiltrar_por_data_de(String filtrar_por_data_de) {
		this.filtrar_por_data_de = filtrar_por_data_de;
	}

	public String getFiltrar_por_data_ate() {
		return filtrar_por_data_ate;
	}

	public void setFiltrar_por_data_ate(String filtrar_por_data_ate) {
		this.filtrar_por_data_ate = filtrar_por_data_ate;
	}
}
