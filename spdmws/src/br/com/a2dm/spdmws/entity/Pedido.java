package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;
import java.util.Date;

public class Pedido
{
	private BigInteger idPedido;
	
	private BigInteger idCliente;
	
	private String desCliente;
	
	private Date dataPedido;
	
	private String observacao;
	
	private String desUsrCad;

	public BigInteger getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(BigInteger idPedido) {
		this.idPedido = idPedido;
	}

	public BigInteger getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(BigInteger idCliente) {
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
