package br.com.a2dm.spdmws.entity;

import java.util.Date;

public class Pedido {
	private Long idPedido;

	private Long idCliente;

	private String desCliente;

	private Date dataPedido;

	private String observacao;

	private String desUsrCad;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getDesCliente() {
		return desCliente;
	}

	public void setDesCliente(String desCliente) {
		this.desCliente = desCliente;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDesUsrCad() {
		return desUsrCad;
	}

	public void setDesUsrCad(String desUsrCad) {
		this.desUsrCad = desUsrCad;
	}
}
