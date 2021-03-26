package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class TabelaPrecoPayload {

	private BigInteger nCodTabPreco;
	private String cNome;

	public TabelaPrecoPayload() {
		super();
	}

	public TabelaPrecoPayload(BigInteger nCodTabPreco, String cNome) {
		super();
		this.nCodTabPreco = nCodTabPreco;
		this.cNome = cNome;
	}

	public BigInteger getnCodTabPreco() {
		return nCodTabPreco;
	}

	public void setnCodTabPreco(BigInteger nCodTabPreco) {
		this.nCodTabPreco = nCodTabPreco;
	}

	public String getcNome() {
		return cNome;
	}

	public void setcNome(String cNome) {
		this.cNome = cNome;
	}

	@Override
	public String toString() {
		return "TabelaPrecoPayload [nCodTabPreco=" + nCodTabPreco + ", cNome=" + cNome + "]";
	}
}
