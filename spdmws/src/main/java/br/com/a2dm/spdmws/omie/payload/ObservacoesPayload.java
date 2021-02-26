package br.com.a2dm.spdmws.omie.payload;

public class ObservacoesPayload {

	private String obs_venda;

	public ObservacoesPayload(String obs_venda) {
		super();
		this.obs_venda = obs_venda;
	}

	public String getObs_venda() {
		return obs_venda;
	}

	public void setObs_venda(String obs_venda) {
		this.obs_venda = obs_venda;
	}
}
