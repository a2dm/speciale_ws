package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class InativarPayloadPedido {

	private BigInteger cCodIntPed;
	private BigInteger nCodPed;

	public InativarPayloadPedido(BigInteger cCodIntPed, BigInteger nCodPed) {
		super();
		this.cCodIntPed = cCodIntPed;
		this.nCodPed = nCodPed;
	}

	public BigInteger getcCodIntPed() {
		return cCodIntPed;
	}

	public void setcCodIntPed(BigInteger cCodIntPed) {
		this.cCodIntPed = cCodIntPed;
	}

	public BigInteger getnCodPed() {
		return nCodPed;
	}

	public void setnCodPed(BigInteger nCodPed) {
		this.nCodPed = nCodPed;
	}
}
