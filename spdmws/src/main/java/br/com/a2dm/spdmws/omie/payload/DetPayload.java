package br.com.a2dm.spdmws.omie.payload;

public class DetPayload {

	private IdePayload ide;
	private ProdutoPayload produto;

	public DetPayload(IdePayload ide, ProdutoPayload produto) {
		super();
		this.ide = ide;
		this.produto = produto;
	}

	public IdePayload getIde() {
		return ide;
	}

	public void setIde(IdePayload ide) {
		this.ide = ide;
	}

	public ProdutoPayload getProduto() {
		return produto;
	}

	public void setProduto(ProdutoPayload produto) {
		this.produto = produto;
	}
}
