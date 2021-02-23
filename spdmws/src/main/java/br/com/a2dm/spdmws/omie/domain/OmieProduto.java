package br.com.a2dm.spdmws.omie.domain;

public class OmieProduto {
	
	private long nCodProd;

	public OmieProduto() {
	}
	
	public OmieProduto(long nCodProd) {
		this.nCodProd = nCodProd;
	}
	
	public long getnCodProd() {
		return nCodProd;
	}
	
	public void setnCodProd(long nCodProd) {
		this.nCodProd = nCodProd;
	}

}
