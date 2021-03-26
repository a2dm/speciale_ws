package br.com.a2dm.spdmws.omie.domain;

import java.math.BigInteger;

public class OmieTabelaPrecoAll {

	private BigInteger nPagina;
	private BigInteger nRegPorPagina;

	public OmieTabelaPrecoAll() {
	}

	public OmieTabelaPrecoAll(BigInteger nPagina, BigInteger nRegPorPagina) {
		super();
		this.nPagina = nPagina;
		this.nRegPorPagina = nRegPorPagina;
	}

	public BigInteger getnPagina() {
		return nPagina;
	}

	public void setnPagina(BigInteger nPagina) {
		this.nPagina = nPagina;
	}

	public BigInteger getnRegPorPagina() {
		return nRegPorPagina;
	}

	public void setnRegPorPagina(BigInteger nRegPorPagina) {
		this.nRegPorPagina = nRegPorPagina;
	}

}
