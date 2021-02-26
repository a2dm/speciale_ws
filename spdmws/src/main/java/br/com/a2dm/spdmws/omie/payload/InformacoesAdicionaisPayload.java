package br.com.a2dm.spdmws.omie.payload;

public class InformacoesAdicionaisPayload {

	private String codigo_categoria;
	private long codigo_conta_corrente;
	private String consumidor_final;
	private String enviar_email;

	public InformacoesAdicionaisPayload(String codigo_categoria, long codigo_conta_corrente, String consumidor_final,
			String enviar_email) {
		super();
		this.codigo_categoria = codigo_categoria;
		this.codigo_conta_corrente = codigo_conta_corrente;
		this.consumidor_final = consumidor_final;
		this.enviar_email = enviar_email;
	}

	public String getCodigo_categoria() {
		return codigo_categoria;
	}

	public void setCodigo_categoria(String codigo_categoria) {
		this.codigo_categoria = codigo_categoria;
	}

	public long getCodigo_conta_corrente() {
		return codigo_conta_corrente;
	}

	public void setCodigo_conta_corrente(long codigo_conta_corrente) {
		this.codigo_conta_corrente = codigo_conta_corrente;
	}

	public String getConsumidor_final() {
		return consumidor_final;
	}

	public void setConsumidor_final(String consumidor_final) {
		this.consumidor_final = consumidor_final;
	}

	public String getEnviar_email() {
		return enviar_email;
	}

	public void setEnviar_email(String enviar_email) {
		this.enviar_email = enviar_email;
	}

}
