package br.com.a2dm.spdmws.omie.payload;

public class DadosBancariosPayload {

	private String agencia;
	private String codigo_banco;
	private String conta_corrente;
	private String doc_titular;
	private String nome_titular;

	public DadosBancariosPayload() {
		super();
	}

	public DadosBancariosPayload(String agencia, String codigo_banco, String conta_corrente, String doc_titular,
			String nome_titular) {
		super();
		this.agencia = agencia;
		this.codigo_banco = codigo_banco;
		this.conta_corrente = conta_corrente;
		this.doc_titular = doc_titular;
		this.nome_titular = nome_titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public String getConta_corrente() {
		return conta_corrente;
	}

	public void setConta_corrente(String conta_corrente) {
		this.conta_corrente = conta_corrente;
	}

	public String getDoc_titular() {
		return doc_titular;
	}

	public void setDoc_titular(String doc_titular) {
		this.doc_titular = doc_titular;
	}

	public String getNome_titular() {
		return nome_titular;
	}

	public void setNome_titular(String nome_titular) {
		this.nome_titular = nome_titular;
	}

	@Override
	public String toString() {
		return "DadosBancariosPayload [agencia=" + agencia + ", codigo_banco=" + codigo_banco + ", conta_corrente="
				+ conta_corrente + ", doc_titular=" + doc_titular + ", nome_titular=" + nome_titular + "]";
	}
}
