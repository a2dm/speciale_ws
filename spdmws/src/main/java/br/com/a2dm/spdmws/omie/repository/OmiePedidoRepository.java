package br.com.a2dm.spdmws.omie.repository;

import java.math.BigInteger;

import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdm.entity.Cliente;
import br.com.a2dm.spdm.service.ClienteService;
import br.com.a2dm.spdmws.api.ApiClientResponse;
import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.omie.api.OmieApiClient;
import br.com.a2dm.spdmws.omie.builder.OmiePedidoBuilder;
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
			JSONObject pedidoOmie = new OmiePedidoBuilder().buildPedido(pedidoDTO);
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
