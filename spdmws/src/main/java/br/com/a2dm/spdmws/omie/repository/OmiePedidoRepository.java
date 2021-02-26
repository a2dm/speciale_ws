package br.com.a2dm.spdmws.omie.repository;

import java.math.BigInteger;

import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdm.entity.Cliente;
import br.com.a2dm.spdm.service.ClienteService;
import br.com.a2dm.spdmws.api.ApiClientResponse;
import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.omie.api.OmieApiClient;
import br.com.a2dm.spdmws.omie.builder.OmiePedidoBuilder;
import br.com.a2dm.spdmws.omie.payload.PedidoPayload;
import br.com.a2dm.spdmws.omie.payload.PesquisarPayloadPedido;
import br.com.a2dm.spdmws.utils.JsonUtils;

public class OmiePedidoRepository {

	private static OmiePedidoRepository instance;
	
	private OmiePedidoRepository() {
	}
	
	public static OmiePedidoRepository getInstance() {
		if(instance == null) {
			instance = new OmiePedidoRepository();
		}
		return instance;
	}
	
	public PedidoDTO pesquisarPedidoCliente(BigInteger idCliente, BigInteger idPedido, String dataPedido) throws OmieRepositoryException {
		try {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			cliente = ClienteService.getInstancia().get(cliente, 0);
			return this.pesquisarPedido(idCliente, cliente.getIdExternoOmie(), idPedido, dataPedido);
		} catch (Exception e) {
			throw new OmieRepositoryException(e);
		}
	}
	
	protected PedidoDTO pesquisarPedido(BigInteger idCliente, BigInteger idExternoOmie, BigInteger idPedido, String dataPedido) throws OmieRepositoryException {
		try {
			PesquisarPayloadPedido pesquisarPedidoOmie = new OmiePedidoBuilder().buildPesquisarPedido(idExternoOmie, idPedido, dataPedido);
			OmieApiClient apiClient = new OmieApiClient();
			ApiClientResponse response = apiClient.post("/produtos/pedido/", "ListarPedidos", pesquisarPedidoOmie);
			return new OmiePedidoBuilder().buildPesquisarPedidoResponse(response.getBody(),idCliente);
		} catch (Exception e) {
			throw new OmieRepositoryException(e);
		}
	}
	
	public PedidoDTO cadastrarPedidoCliente(PedidoDTO pedidoDTO) throws OmieRepositoryException {
		try {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(pedidoDTO.getIdCliente());
			cliente = ClienteService.getInstancia().get(cliente, 0);
			pedidoDTO.setIdExternoOmie(cliente.getIdExternoOmie());
			return this.cadastrarPedido(pedidoDTO);
		} catch (Exception e) {
			throw new OmieRepositoryException(String.format("Erro ao cadastrar pedido para cliente %d", pedidoDTO.getIdCliente()), e);
		}
	}
	
	protected PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO) throws OmieRepositoryException {
		try {
			PedidoPayload pedidoOmie = new OmiePedidoBuilder().buildPedido(pedidoDTO);
			OmieApiClient apiClient = new OmieApiClient();
			ApiClientResponse response = apiClient.post("/produtos/pedido/", "IncluirPedido", pedidoOmie);
			JSONObject json = JsonUtils.parse(response.getBody());
			BigInteger numeroPedido = new BigInteger(json.getString("numero_pedido"));
			pedidoDTO.setIdPedido(numeroPedido);
			return pedidoDTO;
		} catch (Exception e) {
			throw new OmieRepositoryException(String.format("Erro ao cadastrar pedido para cliente %d", pedidoDTO.getIdCliente()), e);
		}
	}

}
