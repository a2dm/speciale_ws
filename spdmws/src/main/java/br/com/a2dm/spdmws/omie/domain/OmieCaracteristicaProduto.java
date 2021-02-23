package br.com.a2dm.spdmws.omie.domain;

import java.math.BigInteger;

public class OmieCaracteristicaProduto {
	
	public static final String LOTE_MINIMO = "Lote Mínimo";
	public static final String QTD_MULTIPLO = "Qtd Múltiplo";

	private long nCodCaract;
	private long nCodProd;
	private String cNomeCaract;
	private String cConteudo;
	
	public OmieCaracteristicaProduto() {
	}

	public long getnCodCaract() {
		return nCodCaract;
	}

	public void setnCodCaract(long nCodCaract) {
		this.nCodCaract = nCodCaract;
	}

	public long getnCodProd() {
		return nCodProd;
	}

	public void setnCodProd(long nCodProd) {
		this.nCodProd = nCodProd;
	}

	public String getcNomeCaract() {
		return cNomeCaract;
	}

	public void setcNomeCaract(String cNomeCaract) {
		this.cNomeCaract = cNomeCaract;
	}

	public String getcConteudo() {
		return cConteudo;
	}

	public void setcConteudo(String cConteudo) {
		this.cConteudo = cConteudo;
	}
	
	public BigInteger conteudoToBigInteger() {
		return new BigInteger(this.cConteudo.trim());
	}

}
