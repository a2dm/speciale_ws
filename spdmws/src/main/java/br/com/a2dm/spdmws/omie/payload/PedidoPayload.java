package br.com.a2dm.spdmws.omie.payload;

import java.util.List;

public class PedidoPayload {

	private CabecalhoPayload cabecalho;
	private List<DetPayload> det;
	private FretePayload frete;
	private ObservacoesPayload observacoes;
	private InformacoesAdicionaisPayload informacoes_adicionais;

	public PedidoPayload(CabecalhoPayload cabecalho, List<DetPayload> det, FretePayload frete,
			ObservacoesPayload observacoes, InformacoesAdicionaisPayload informacoes_adicionais) {
		super();
		this.cabecalho = cabecalho;
		this.det = det;
		this.frete = frete;
		this.observacoes = observacoes;
		this.informacoes_adicionais = informacoes_adicionais;
	}

	public CabecalhoPayload getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(CabecalhoPayload cabecalho) {
		this.cabecalho = cabecalho;
	}

	public List<DetPayload> getDet() {
		return det;
	}

	public void setDet(List<DetPayload> det) {
		this.det = det;
	}

	public FretePayload getFrete() {
		return frete;
	}

	public void setFrete(FretePayload frete) {
		this.frete = frete;
	}

	public ObservacoesPayload getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(ObservacoesPayload observacoes) {
		this.observacoes = observacoes;
	}

	public InformacoesAdicionaisPayload getInformacoes_adicionais() {
		return informacoes_adicionais;
	}

	public void setInformacoes_adicionais(InformacoesAdicionaisPayload informacoes_adicionais) {
		this.informacoes_adicionais = informacoes_adicionais;
	}
}
