package br.com.a2dm.spdmws.omie;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import br.com.a2dm.brcmn.domain.OmieProduto;
import br.com.a2dm.brcmn.domain.OmieTabelaPreco;
import br.com.a2dm.spdm.api.ApiClientResponse;
import br.com.a2dm.spdm.omie.api.OmieApiClient;
import br.com.a2dm.spdm.utils.JsonUtils;

public class IntegracaoOmieApiTest {

	public IntegracaoOmieApiTest() {
	}

	@Test
	public void listagemProdutos() {

		try {
			
			OmieTabelaPreco tabelaPreco = new OmieTabelaPreco();
			tabelaPreco.setnCodTabPreco("1621070468");
			
			OmieApiClient apiClient = new OmieApiClient();
			ApiClientResponse response = apiClient.post("/produtos/tabelaprecos/", "ListarTabelaItens", tabelaPreco);
						
			JSONObject jsonObject = JsonUtils.parse(response.getBody());
			JSONObject listaTabelaPreco = (JSONObject)jsonObject.get("listaTabelaPreco");
			JSONArray itensTabela = (JSONArray) listaTabelaPreco.getJSONArray("itensTabela");
			
			for(int i = 0; i < itensTabela.length(); i++) {
				JSONObject produto = (JSONObject) itensTabela.get(i);
				System.out.println(String.format("%d - %s", produto.getLong("nCodProd"), produto.get("cDescricaoProduto")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listagemCaracteristicasProdutos() {

		try {
			
			OmieProduto produto = new OmieProduto();
			produto.setnCodProd(1620175276);
			
			OmieApiClient apiClient = new OmieApiClient();
			ApiClientResponse response = apiClient.post("/geral/prodcaract/", "ListarCaractProduto", produto);
			
			JSONObject jsonObject = JsonUtils.parse(response.getBody());
			JSONArray listaCaracteristicas = (JSONArray) jsonObject.getJSONArray("listaCaracteristicas");
			
			for(int i = 0; i < listaCaracteristicas.length(); i++) {
				JSONObject caracteristica = (JSONObject) listaCaracteristicas.get(i);
				System.out.println(
					String.format("%d - %s - %s",caracteristica.getLong("nCodProd"), caracteristica.get("cNomeCaract"),
							caracteristica.get("cConteudo")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
