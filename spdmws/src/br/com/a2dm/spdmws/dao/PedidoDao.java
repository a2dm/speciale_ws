package br.com.a2dm.spdmws.dao;

import br.com.a2dm.spdmws.entity.PedidoCompleto;

public interface PedidoDao
{	
	public PedidoCompleto getPedido(String dataPedido, Long idCliente) throws Exception;	
}
