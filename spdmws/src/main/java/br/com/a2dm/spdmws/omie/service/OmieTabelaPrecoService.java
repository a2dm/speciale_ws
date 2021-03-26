package br.com.a2dm.spdmws.omie.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.a2dm.spdmws.omie.payload.TabelaPrecoPayload;
import br.com.a2dm.spdmws.omie.repository.OmieTabelasPrecosRepository;;

public class OmieTabelaPrecoService {

	private static OmieTabelaPrecoService instance;

	private OmieTabelaPrecoService() {
	}

	public static OmieTabelaPrecoService getInstance() {
		if (instance == null) {
			instance = new OmieTabelaPrecoService();
		}
		return instance;
	}

	public List<TabelaPrecoPayload> listarTabelasPrecos() throws OmieServiceException {
		try {
			return OmieTabelasPrecosRepository.getInstance().listarTabelasPrecos();
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}
	
	public TabelaPrecoPayload obterTabelaPreco(String tabelaPreco) throws OmieServiceException {
		List<TabelaPrecoPayload> listTabelaPreco = listarTabelasPrecos();
		List<TabelaPrecoPayload> listTabelaPrecoFiltrada = listTabelaPreco.stream()
																		  .filter(x -> x.getcNome().equalsIgnoreCase(tabelaPreco))
																		  .collect(Collectors.toList());
		
		return isResultTabelaPreco(listTabelaPrecoFiltrada) ? listTabelaPrecoFiltrada.get(0) : null;
	}

	private boolean isResultTabelaPreco(List<TabelaPrecoPayload> listTabelaPrecoFiltrada) {
		return listTabelaPrecoFiltrada != null;
	}
}
