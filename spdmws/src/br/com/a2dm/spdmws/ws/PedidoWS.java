package br.com.a2dm.spdmws.ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.spdmws.dao.impl.PedidoDaoImpl;
import br.com.a2dm.spdmws.entity.PedidoCompleto;

@Path("/PedidoWS")
public class PedidoWS
{
	@POST
	@Path("/getPedido")	
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoCompleto getPedido(@FormParam("dataPedido") String dataPedido, @FormParam("idCliente") Long idCliente)
	{
		PedidoCompleto pedidoCompleto = null;
		
		try
		{
			pedidoCompleto = PedidoDaoImpl.getInstancia().getPedido(dataPedido, idCliente);
			
		}
		catch (Exception ex)
		{
			//ex.getMessage();
		}
		
		return pedidoCompleto;
	}
}