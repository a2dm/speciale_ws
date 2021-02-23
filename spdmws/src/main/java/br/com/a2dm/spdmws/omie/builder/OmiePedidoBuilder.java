package br.com.a2dm.spdmws.omie.builder;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.dto.ProdutoDTO;
import br.com.a2dm.spdmws.utils.DateUtils;

public class OmiePedidoBuilder {

	public OmiePedidoBuilder() {
	}
	public JSONObject buildPedido(PedidoDTO pedidoDTO) throws OmieBuilderException {
		try {
			return new JSONObject()
					.put("cabecalho", buildCabecalhoPedido(pedidoDTO))
					.put("det", buildProdutos(pedidoDTO))
					.put("frete", buildFretePedido(pedidoDTO))
					.put("observacoes", buildObservacoesPedido(pedidoDTO))
					.put("informacoes_adicionais", buildInformacoesAdicionaisPedido());
		} catch (Exception e) {
			throw new OmieBuilderException("Erro ao montar json para Pedido", e);
		}
	}

	protected JSONObject buildCabecalhoPedido(PedidoDTO pedidoDTO) throws JSONException {
		return new JSONObject()
				.put("codigo_pedido_integracao", createCodPedidoIntegracao(pedidoDTO) )
				.put("codigo_cliente", pedidoDTO.getIdExternoOmie())
				.put("data_previsao", DateUtils.formatDatePtBr(pedidoDTO.getDataPedido()))
				.put("etapa", "10")
				.put("codigo_parcela", "000")
				.put("quantidade_itens", pedidoDTO.getProdutos().size());

	}
	
	protected long createCodPedidoIntegracao(PedidoDTO pedidoDTO) {
		return System.currentTimeMillis();
	}
	
	protected JSONArray buildProdutos(PedidoDTO pedidoDTO) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		if(pedidoDTO.getProdutos() != null && !pedidoDTO.getProdutos().isEmpty()) {
			for(ProdutoDTO produtoDTO : pedidoDTO.getProdutos()) {
				jsonArray.put(buildProdutoPedido(produtoDTO));				
			}
		}
		return jsonArray;
	}
	
	protected JSONObject buildProdutoPedido(ProdutoDTO produtoDTO) throws JSONException {
		JSONObject ide = new JSONObject()
				.put("codigo_item_integracao", produtoDTO.getIdProduto());
		JSONObject produto = new JSONObject()
				.put("cfop", "5.401")
				.put("ncm", "1905.20.90")
				.put("codigo_produto", produtoDTO.getIdProduto())
				.put("descricao", produtoDTO.getDesProduto())
				.put("quantidade", produtoDTO.getQtdSolicitada())
				.put("tipo_desconto", "V")
				.put("unidade", "UN")
				.put("valor_desconto", "0")
				.put("valor_unitario", produtoDTO.getValorUnitario());
		return new JSONObject()
				.put("ide", ide)
				.put("produto", produto);
	}
	
	protected JSONObject buildFretePedido(PedidoDTO pedidoDTO) throws JSONException {
		return new JSONObject()
				.put("modalidade", getOpcaoFrete(pedidoDTO));
	}
	
	protected String getOpcaoFrete(PedidoDTO pedidoDTO) {
		return pedidoDTO.isRetirada() ? "9" : "1";
	}
	
	protected JSONObject buildInformacoesAdicionaisPedido() throws JSONException {
		return new JSONObject()
				.put("codigo_categoria", "1.01.02")
				.put("codigo_conta_corrente", 1617989193)
				.put("consumidor_final", "S")
				.put("enviar_email", "N");
	}
	
	protected JSONObject buildObservacoesPedido(PedidoDTO pedidoDTO) throws JSONException {
		return new JSONObject()
				.put("obs_venda", pedidoDTO.getObservacao());
	}

}
