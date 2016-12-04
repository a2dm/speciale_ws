package br.com.a2dm.spdmws.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoProdutoParam {

	@XmlElement(required=true)
	@JsonProperty("idClienteLogado")
	private BigInteger idClienteLogado;

	@XmlElement(required=true)
	@JsonProperty("data")
	private Date data;

	@JsonProperty("observacao")
	private String observacao;

	public BigInteger getIdClienteLogado() {
		return idClienteLogado;
	}

	public void setIdClienteLogado(BigInteger idClienteLogado) {
		this.idClienteLogado = idClienteLogado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
