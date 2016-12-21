package br.com.a2dm.spdmws.dao;

import java.util.Date;
import java.util.List;

import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;

public interface PedidoDao
{	
	PedidoCompleto getPedido(Long idPedido, Long idCliente, Date dataPedido) throws Exception;
	
	PedidoCompleto getPedido(Long idCliente, Date dataPedido) throws Exception;

	List<ProdutoCliente> getListaProdutoByCliente(Long idCliente) throws Exception;	
}
