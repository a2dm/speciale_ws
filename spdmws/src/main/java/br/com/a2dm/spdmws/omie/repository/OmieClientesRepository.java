package br.com.a2dm.spdmws.omie.repository;

import java.util.List;

import br.com.a2dm.spdmws.api.ApiClientResponse;
import br.com.a2dm.spdmws.dto.ClienteDTO;
import br.com.a2dm.spdmws.omie.api.OmieApiClient;
import br.com.a2dm.spdmws.omie.builder.OmieClienteBuilder;
import br.com.a2dm.spdmws.omie.payload.PesquisarPayloadCliente;

public class OmieClientesRepository {

	private static OmieClientesRepository instance;
	
	private OmieClientesRepository() {
	}
	
	public static OmieClientesRepository getInstance() {
		if(instance == null) {
			instance = new OmieClientesRepository();
		}
		return instance;
	}
	
	public List<ClienteDTO> pesquisarClientes(String nomeCliente) throws OmieRepositoryException {
		try {
			PesquisarPayloadCliente pesquisarPedidoOmie = new OmieClienteBuilder().buildPesquisarCliente(nomeCliente);
			OmieApiClient apiClient = new OmieApiClient();
			ApiClientResponse response = apiClient.post("/geral/clientes/", "ListarClientesResumido", pesquisarPedidoOmie);
			return new OmieClienteBuilder().buildPesquisarClienteResponse(response.getBody());
		} catch (Exception e) {
			throw new OmieRepositoryException(e);
		}
	}
}
