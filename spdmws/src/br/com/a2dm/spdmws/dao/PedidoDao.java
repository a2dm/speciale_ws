package br.com.a2dm.spdmws.dao;

import java.util.List;

import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;

public interface PedidoDao
{	
	PedidoCompleto getPedido(String dataPedido, Long idCliente) throws Exception;

	List<ProdutoCliente> getListaProdutoByCliente(Long idCliente) throws Exception;	
}
