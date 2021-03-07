package br.com.a2dm.spdmws.omie.service;

import java.util.List;

import br.com.a2dm.spdmws.dto.ClienteDTO;
import br.com.a2dm.spdmws.omie.repository.OmieClientesRepository;

public class OmieClienteService {

	private static OmieClienteService instance;

	private OmieClienteService() {
	}

	public static OmieClienteService getInstance() {
		if (instance == null) {
			instance = new OmieClienteService();
		}
		return instance;
	}
	
	public List<ClienteDTO> pesquisarClientes(String nomeCliente) throws OmieServiceException {
		try {
			return OmieClientesRepository.getInstance().pesquisarClientes(nomeCliente);
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}
}
