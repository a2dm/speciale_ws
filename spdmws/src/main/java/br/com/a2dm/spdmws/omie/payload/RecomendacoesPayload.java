package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class RecomendacoesPayload {

	private BigInteger codigo_vendedor;
	private String email_fatura;
	private String gerar_boletos;
	private String numero_parcelas;

	public RecomendacoesPayload() {
		super();
	}

	public RecomendacoesPayload(BigInteger codigo_vendedor, String email_fatura, String gerar_boletos,
			String numero_parcelas) {
		super();
		this.codigo_vendedor = codigo_vendedor;
		this.email_fatura = email_fatura;
		this.gerar_boletos = gerar_boletos;
		this.numero_parcelas = numero_parcelas;
	}

	public BigInteger getCodigo_vendedor() {
		return codigo_vendedor;
	}

	public void setCodigo_vendedor(BigInteger codigo_vendedor) {
		this.codigo_vendedor = codigo_vendedor;
	}

	public String getEmail_fatura() {
		return email_fatura;
	}

	public void setEmail_fatura(String email_fatura) {
		this.email_fatura = email_fatura;
	}

	public String getGerar_boletos() {
		return gerar_boletos;
	}

	public void setGerar_boletos(String gerar_boletos) {
		this.gerar_boletos = gerar_boletos;
	}

	public String getNumero_parcelas() {
		return numero_parcelas;
	}

	public void setNumero_parcelas(String numero_parcelas) {
		this.numero_parcelas = numero_parcelas;
	}

	@Override
	public String toString() {
		return "RecomendacoesPayload [codigo_vendedor=" + codigo_vendedor + ", email_fatura=" + email_fatura
				+ ", gerar_boletos=" + gerar_boletos + ", numero_parcelas=" + numero_parcelas + "]";
	}
}
