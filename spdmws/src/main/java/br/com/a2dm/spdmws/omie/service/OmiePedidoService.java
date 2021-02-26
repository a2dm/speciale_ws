package br.com.a2dm.spdmws.omie.service;

import java.math.BigInteger;

import br.com.a2dm.spdmws.dto.PedidoDTO;
import br.com.a2dm.spdmws.omie.repository.OmiePedidoRepository;

public class OmiePedidoService {

	private static OmiePedidoService instance;

	private OmiePedidoService() {
	}

	public static OmiePedidoService getInstance() {
		if (instance == null) {
			instance = new OmiePedidoService();
		}
		return instance;
	}
	
	public PedidoDTO pesquisarPedido(BigInteger idCliente, BigInteger idPedido, String dataPedido) throws OmieServiceException {
		try {
			return OmiePedidoRepository.getInstance().pesquisarPedidoCliente(idCliente, idPedido, dataPedido);
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}

	public PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO) throws OmieServiceException {
		try {
			return OmiePedidoRepository.getInstance().cadastrarPedidoCliente(pedidoDTO);
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}
}
