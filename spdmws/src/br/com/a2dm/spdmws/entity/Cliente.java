package br.com.a2dm.spdmws.entity;

import java.io.Serializable;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idCliente;

	private String desCliente;

	private String horLimite;

	private String flgAtivo;

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

	public String getHorLimite() {
		return horLimite;
	}

	public void setHorLimite(String horLimite) {
		this.horLimite = horLimite;
	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}
}