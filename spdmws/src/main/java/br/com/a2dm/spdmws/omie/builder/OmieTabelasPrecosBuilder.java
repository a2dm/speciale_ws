package br.com.a2dm.spdmws.omie.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdmws.omie.payload.TabelaPrecoPayload;
import br.com.a2dm.spdmws.utils.JsonUtils;

public class OmieTabelasPrecosBuilder {

	public OmieTabelasPrecosBuilder() {
	}

	public List<TabelaPrecoPayload> buildTabelasPrecos(String json) {
		try {
			List<TabelaPrecoPayload> tabelasPrecos = new ArrayList<>();
			
			JSONObject jsonObject = JsonUtils.parse(json);
			JSONArray itensTabela = (JSONArray) jsonObject.getJSONArray("listaTabelasPreco");
			
			for (int i = 0; i < itensTabela.length(); i++) {
				JSONObject tabelaPrecoObj = (JSONObject) itensTabela.get(i);
				
				TabelaPrecoPayload tabelaPreco = new TabelaPrecoPayload();
				tabelaPreco.setnCodTabPreco(new BigInteger(tabelaPrecoObj.getString("nCodTabPreco")));
				tabelaPreco.setcNome(tabelaPrecoObj.getString("cNome"));
				
				tabelasPrecos.add(tabelaPreco);
			}
			return tabelasPrecos;
		} catch (Exception e) {
			throw new OmieBuilderException(e);
		}
	}
}
