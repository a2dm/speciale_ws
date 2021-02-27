package br.com.a2dm.spdmws.omie.builder;

import static br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto.LOTE_MINIMO;
import static br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto.QTD_MULTIPLO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.dto.ProdutoDTO;
import br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto;
import br.com.a2dm.spdmws.omie.payload.CabecalhoPayload;
import br.com.a2dm.spdmws.omie.payload.ConsultarPayload;
import br.com.a2dm.spdmws.omie.payload.DetPayload;
import br.com.a2dm.spdmws.omie.payload.FretePayload;
import br.com.a2dm.spdmws.omie.payload.IdePayload;
import br.com.a2dm.spdmws.omie.payload.InativarPayloadPedido;
import br.com.a2dm.spdmws.omie.payload.InformacoesAdicionaisPayload;
import br.com.a2dm.spdmws.omie.payload.ObservacoesPayload;
import br.com.a2dm.spdmws.omie.payload.PedidoPayload;
import br.com.a2dm.spdmws.omie.payload.PesquisarPayloadPedido;
import br.com.a2dm.spdmws.omie.payload.ProdutoPayload;
import br.com.a2dm.spdmws.omie.repository.OmieProdutosRepository;
import br.com.a2dm.spdmws.utils.DateUtils;
import br.com.a2dm.spdmws.utils.JsonUtils;

public class OmiePedidoBuilder {

	public OmiePedidoBuilder() {
		
	}
	
	public PesquisarPayloadPedido buildPesquisarPedido(BigInteger idExternoOmie, BigInteger idPedido, String dataPedido) throws OmieBuilderException {
		try {
			String dataStr = "";
			if (!dataPedido.isEmpty()) {
				Date data = DateUtils.parseDate(dataPedido, "yyyy-MM-dd");
				dataStr = DateUtils.formatDate(data, "dd/MM/yyyy");
			}
			return new PesquisarPayloadPedido(idExternoOmie, idPedido, dataStr);
		} catch (Exception e) {
			throw new OmieBuilderException("Erro ao montar json para Pesquisar Pedido", e);
		}
	}

	public PedidoDTO buildPesquisarPedidoResponse(String json, BigInteger idCliente) {
		try {
			PedidoDTO pedidoDTO = new PedidoDTO();
			JSONObject jsonObject = JsonUtils.parse(json);
			JSONArray pedidosVendaProduto = (JSONArray) jsonObject.getJSONArray("pedido_venda_produto");
			
			for (int i = 0; i < pedidosVendaProduto.length(); i++) {
				JSONObject pedidoJson = (JSONObject) pedidosVendaProduto.get(i);
				JSONObject cabecalho = (JSONObject) pedidoJson.get("cabecalho");
				JSONObject observacoes = (JSONObject) pedidoJson.get("observacoes");
				JSONObject frete = (JSONObject) pedidoJson.get("frete");
				JSONObject infoCadastro = (JSONObject) pedidoJson.get("infoCadastro");
				JSONArray detalhes = (JSONArray) pedidoJson.getJSONArray("det");
				
				pedidoDTO = this.buildPedidoDTO(cabecalho, observacoes, frete, infoCadastro, idCliente);

				for (int j = 0; j < detalhes.length(); j++) {
					JSONObject detalheJson = (JSONObject) detalhes.get(j);
					JSONObject produto = (JSONObject) detalheJson.get("produto");
					ProdutoDTO produtoDTO = this.buildProdutoDTO(produto);
					pedidoDTO.getProdutos().add(produtoDTO);
				}
			}
			return pedidoDTO;
		} catch (Exception e) {
			throw new OmieBuilderException(e);
		}
	}
	
	public ConsultarPayload buildConsultarPedido(BigInteger idPedido) throws OmieBuilderException {
		try {
			return new ConsultarPayload(idPedido);
		} catch (Exception e) {
			throw new OmieBuilderException("Erro ao montar json para Consultar Pedido", e);
		}
	}
	
	public InativarPayloadPedido buildInativarPedido(BigInteger codigoPedidoIntegracao, BigInteger codigoPedido) throws OmieBuilderException {
		try {
			return new InativarPayloadPedido(codigoPedidoIntegracao, codigoPedido);
		} catch (Exception e) {
			throw new OmieBuilderException("Erro ao montar json para Inativar Pedido", e);
		}
	}
	
	public PedidoDTO buildPedidoDTO(JSONObject cabecalho, JSONObject observacao, JSONObject frete, JSONObject infoCadastro, BigInteger idCliente) throws Exception {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDataPedido(DateUtils.parseDatePtBr(cabecalho.getString("data_previsao")));
		pedidoDTO.setObservacao(observacao.getString("obs_venda"));
		pedidoDTO.setIdCliente(idCliente);
		pedidoDTO.setIdPedido(new BigInteger(cabecalho.getString("numero_pedido")));
		pedidoDTO.setCodigoPedido(new BigInteger(cabecalho.getString("codigo_pedido")));
		pedidoDTO.setCodigoPedidoIntegracao(new BigInteger(cabecalho.getString("codigo_pedido_integracao")));
		pedidoDTO.setIdOpcaoEntrega(new BigInteger(frete.getInt("modalidade") == 1 ? "2" : "1"));
		pedidoDTO.setFlgAtivo(infoCadastro.getString("cancelado").equalsIgnoreCase("N") ? "S" : "N");
		pedidoDTO.setProdutos(new ArrayList<>());
		return pedidoDTO;
	}
	
	public ProdutoDTO buildProdutoDTO(JSONObject produto) throws Exception {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setDesProduto(produto.getString("descricao"));
		produtoDTO.setIdProduto(new BigInteger(produto.getString("codigo_produto")));
		produtoDTO.setQtdSolicitada(new BigInteger(produto.getString("quantidade")));
		produtoDTO.setValorUnitario(produto.getDouble("valor_unitario"));
		
		Map<String, OmieCaracteristicaProduto> caracteristicas = null;
		caracteristicas = OmieProdutosRepository.getInstance().obterCaracteristicasProduto(produtoDTO.getIdProduto());
		
		if(!caracteristicas.isEmpty()) {
			OmieCaracteristicaProduto loteMinimo = caracteristicas.get(LOTE_MINIMO.toLowerCase());
			if (loteMinimo != null) {
				produtoDTO.setQtdLoteMinimo(loteMinimo.conteudoToBigInteger());							
			}
			
			OmieCaracteristicaProduto qtdeMultiplo = caracteristicas.get(QTD_MULTIPLO.toLowerCase());
			if (qtdeMultiplo != null) {
				produtoDTO.setQtdMultiplo(qtdeMultiplo.conteudoToBigInteger());							
			}
		}
		return produtoDTO;
	}
	
	public PedidoPayload buildPedido(PedidoDTO pedidoDTO) throws OmieBuilderException {
		try {
			return new PedidoPayload(buildCabecalhoPedido(pedidoDTO),
					          		 buildProdutos(pedidoDTO),
					                 buildFretePedido(pedidoDTO),
					                 buildObservacoesPedido(pedidoDTO),
					                 buildInformacoesAdicionaisPedido());
		} catch (Exception e) {
			throw new OmieBuilderException("Erro ao montar json para Pedido", e);
		}
	}

	protected CabecalhoPayload buildCabecalhoPedido(PedidoDTO pedidoDTO) throws JSONException {
		return new CabecalhoPayload(pedidoDTO.getCodigoPedidoIntegracao() == null ? createCodPedidoIntegracao(pedidoDTO) : pedidoDTO.getCodigoPedidoIntegracao().longValue(),
									pedidoDTO.getIdExternoOmie(),
									DateUtils.formatDatePtBr(pedidoDTO.getDataPedido()),
									"10",
									"000",
									pedidoDTO.getProdutos().size());
	}
	
	protected long createCodPedidoIntegracao(PedidoDTO pedidoDTO) {
		return System.currentTimeMillis();
	}
	
	protected List<DetPayload> buildProdutos(PedidoDTO pedidoDTO) throws JSONException {
		List<DetPayload> dets = new ArrayList<>();
		if (pedidoDTO.getProdutos() != null && !pedidoDTO.getProdutos().isEmpty()) {
			for (ProdutoDTO produtoDTO : pedidoDTO.getProdutos()) {
				dets.add(buildProdutoPedido(produtoDTO));				
			}
		}
		return dets;
	}
	
	protected DetPayload buildProdutoPedido(ProdutoDTO produtoDTO) throws JSONException {
		IdePayload ide = new IdePayload(produtoDTO.getIdProduto(), buildAcaoItem(produtoDTO));
		
		ProdutoPayload produto = new ProdutoPayload("5.401",
													"1905.20.90",
													produtoDTO.getIdProduto(),
													produtoDTO.getDesProduto(),
													produtoDTO.getQtdSolicitada(),
													"V",
													"UN",
													"0",
													produtoDTO.getValorUnitario());
		return new DetPayload(ide, produto);
	}

	private String buildAcaoItem(ProdutoDTO produtoDTO) {
		String acaoItem = "";
		if (produtoDTO.getFlgAtivo() != null 
				&& !produtoDTO.getFlgAtivo().equalsIgnoreCase("")
				&& produtoDTO.getFlgAtivo().equalsIgnoreCase("N")) {
			acaoItem = "E";
		}
		return acaoItem;
	}
	
	protected FretePayload buildFretePedido(PedidoDTO pedidoDTO) throws JSONException {
		return new FretePayload(getOpcaoFrete(pedidoDTO));
	}
	
	protected InformacoesAdicionaisPayload buildInformacoesAdicionaisPedido() throws JSONException {
		return new InformacoesAdicionaisPayload("1.01.02", 1617989193, "S", "N");
	}
	
	protected ObservacoesPayload buildObservacoesPedido(PedidoDTO pedidoDTO) throws JSONException {
		return new ObservacoesPayload(pedidoDTO.getObservacao());
	}
	
	protected String getOpcaoFrete(PedidoDTO pedidoDTO) {
		return this.isRetirada(pedidoDTO) ? "9" : "1";
	}
	
	protected boolean isRetirada(PedidoDTO pedidoDTO) {
		return pedidoDTO.getIdOpcaoEntrega().toString().equals("1");
	}
}
