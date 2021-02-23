package br.com.a2dm.spdmws.omie.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.a2dm.spdm.entity.Cliente;
import br.com.a2dm.spdm.service.ClienteService;
import br.com.a2dm.spdmws.dto.ProdutoDTO;
import br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto;
import br.com.a2dm.spdmws.omie.repository.OmieProdutosRepository;
import static br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto.LOTE_MINIMO;
import static br.com.a2dm.spdmws.omie.domain.OmieCaracteristicaProduto.QTD_MULTIPLO;;

public class OmieProdutoService {

	private static OmieProdutoService instance;

	private OmieProdutoService() {
	}

	public static OmieProdutoService getInstance() {
		if (instance == null) {
			instance = new OmieProdutoService();
		}
		return instance;
	}

	public List<ProdutoDTO> listarProdutosPorCliente(BigInteger idCliente) throws OmieServiceException {
		try {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			cliente = ClienteService.getInstancia().get(cliente, 0);
			return listarProdutosPorTabelaPreco(cliente.getIdTabelaPrecoOmie());
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}
	
	public List<ProdutoDTO> listarProdutosPorTabelaPreco(BigInteger idTabelaPreco) throws OmieServiceException {
		try {
			List<ProdutoDTO> produtos = OmieProdutosRepository.getInstance().listarProdutosPorTabelaPreco(idTabelaPreco);
			if(produtos != null && !produtos.isEmpty()) {
				Map<String, OmieCaracteristicaProduto> caracteristicas = null;
				for(ProdutoDTO produtoDTO : produtos) {
					caracteristicas = OmieProdutosRepository.getInstance().obterCaracteristicasProduto(produtoDTO.getIdProduto());
					if(!caracteristicas.isEmpty()) {
						
						OmieCaracteristicaProduto loteMinimo = caracteristicas.get(LOTE_MINIMO.toLowerCase());
						if(loteMinimo != null) {
							produtoDTO.setQtdLoteMinimo(loteMinimo.conteudoToBigInteger());							
						}
						
						OmieCaracteristicaProduto qtdeMultiplo = caracteristicas.get(QTD_MULTIPLO.toLowerCase());
						if(qtdeMultiplo != null) {
							produtoDTO.setQtdMultiplo(qtdeMultiplo.conteudoToBigInteger());							
						}
					}
				}
			}
			return produtos;
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}
}
