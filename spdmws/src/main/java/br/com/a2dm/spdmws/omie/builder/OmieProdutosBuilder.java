package br.com.a2dm.spdmws.omie.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdmws.dto.ProdutoDTO;
import br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto;
import br.com.a2dm.spdmws.utils.JsonUtils;

public class OmieProdutosBuilder {

	public OmieProdutosBuilder() {
	}

	public List<ProdutoDTO> buildProdutos(String json) {
		try {
			
			List<ProdutoDTO> produtos = new ArrayList<>();
			
			JSONObject jsonObject = JsonUtils.parse(json);
			JSONObject listaTabelaPreco = (JSONObject)jsonObject.get("listaTabelaPreco");
			JSONArray itensTabela = (JSONArray) listaTabelaPreco.getJSONArray("itensTabela");
			
			for(int i = 0; i < itensTabela.length(); i++) {
				JSONObject produto = (JSONObject) itensTabela.get(i);
				
				ProdutoDTO produtoDTO = new ProdutoDTO();
				produtoDTO.setIdProduto(new BigInteger(produto.getString("nCodProd")));
				produtoDTO.setDesProduto(produto.getString("cDescricaoProduto"));
				produtoDTO.setValorUnitario(produto.getDouble("nValorTabela"));
				produtoDTO.setFlgAtivo("S");
				
				produtos.add(produtoDTO);
			}
			return produtos;
		} catch (Exception e) {
			throw new OmieBuilderException(e);
		}
	}
	
	public Map<String,OmieCaracteristicaProduto> buildProdutoCaracteristicas(String json){
		try {
			
			Map<String, OmieCaracteristicaProduto> caracteristicas = new HashMap<>();
			
			JSONObject jsonObject = JsonUtils.parse(json);
			JSONArray listaCaracteristicas = (JSONArray) jsonObject.getJSONArray("listaCaracteristicas");
			
			for(int i = 0; i < listaCaracteristicas.length(); i++) {
				JSONObject caracteristicaJson = (JSONObject) listaCaracteristicas.get(i);
				
				OmieCaracteristicaProduto caracteristica = new OmieCaracteristicaProduto();
				caracteristica.setcNomeCaract(caracteristicaJson.getString("cNomeCaract"));
				caracteristica.setnCodCaract(caracteristicaJson.getLong("nCodProd"));
				caracteristica.setcConteudo(caracteristicaJson.getString("cConteudo"));
				
				caracteristicas.put(caracteristica.getcNomeCaract().toLowerCase(), caracteristica);
			}
			return caracteristicas;
		} catch (Exception e) {
			throw new OmieBuilderException(e);
		}
	}
	
	
	
}
