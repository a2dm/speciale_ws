package br.com.a2dm.spdmws.omie.payload;

public class ClientesFiltroPayload {

	private String nome_fantasia;

	public ClientesFiltroPayload(String nome_fantasia) {
		super();
		this.nome_fantasia = nome_fantasia;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
}
