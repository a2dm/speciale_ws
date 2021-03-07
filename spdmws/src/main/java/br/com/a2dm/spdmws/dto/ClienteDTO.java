package br.com.a2dm.spdmws.dto;

import java.math.BigInteger;


public class ClienteDTO {

	private BigInteger idCliente;
	private BigInteger idExternoOmie;
	private BigInteger idTabelaPrecoOmie;
	private String nomeCliente;
	private Boolean cadastroIncompleto;

	public BigInteger getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(BigInteger idCliente) {
		this.idCliente = idCliente;
	}

	public BigInteger getIdExternoOmie() {
		return idExternoOmie;
	}

	public void setIdExternoOmie(BigInteger idExternoOmie) {
		this.idExternoOmie = idExternoOmie;
	}

	public BigInteger getIdTabelaPrecoOmie() {
		return idTabelaPrecoOmie;
	}

	public void setIdTabelaPrecoOmie(BigInteger idTabelaPrecoOmie) {
		this.idTabelaPrecoOmie = idTabelaPrecoOmie;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Boolean getCadastroIncompleto() {
		return cadastroIncompleto;
	}

	public void setCadastroIncompleto(Boolean cadastroIncompleto) {
		this.cadastroIncompleto = cadastroIncompleto;
	}
}
