package br.com.a2dm.spdmws.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.a2dm.spdmws.dao.PedidoDao;
import br.com.a2dm.spdmws.dao.factory.ConnectionFactory;
import br.com.a2dm.spdmws.entity.Pedido;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.PedidoProduto;

public class PedidoDaoImpl implements PedidoDao
{
	private static PedidoDaoImpl instancia = null;
	
	private PedidoDaoImpl(){}
	
	public static PedidoDaoImpl getInstancia()
	{
		if (instancia == null)
		{
			instancia = new PedidoDaoImpl();
		}
		return instancia;
	}
	
	
	@Override
	public PedidoCompleto getPedido(String dataPedido, Long idCliente) throws Exception
	{
		Connection conexao = null;
		
		try
		{
			conexao = ConnectionFactory.getConnection();
			
			PedidoCompleto pedidoCompleto = null;
			
			String sql = " select p.id_pedido, p.id_cliente, c.des_cliente, p.dat_pedido, "
					+ "    		  p.obs_pedido, u.nome, pp.id_pedido_produto, pp.id_pedido, pd.des_produto, pp.qtd_solicitada "                                                      
	                + "     from ped.tb_pedido p												  "
					+ "		inner join ped.tb_pedido_produto pp on p.id_pedido = pp.id_pedido     "
					+ "		inner join ped.tb_cliente c on c.id_cliente = p.id_cliente       "
					+ "		inner join seg.tb_usuario u on u.id_usuario = p.id_usuario_cad   "
					+ "		inner join ped.tb_produto pd on pd.id_produto = pp.id_produto    "
					+ "	       where p.flg_ativo = 'S'                                       "          
					+ "		 and pp.flg_ativo = 'S'                                          "     
					+ "		 and p.id_cliente = ?                                            "    
					+ "		 and p.dat_pedido = to_date(?, 'yyyy-MM-dd')";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			//SETAR PARAMETROS
			pst.setLong(1, idCliente.longValue());
			pst.setString(2, dataPedido);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				if(rs.isFirst())
				{
					pedidoCompleto = new PedidoCompleto();
					
					pedidoCompleto.setPedido(new Pedido());
					pedidoCompleto.setListaPedidoProduto(new ArrayList<PedidoProduto>());
					
					pedidoCompleto.getPedido().setIdPedido(new BigInteger(rs.getString(1)));
					pedidoCompleto.getPedido().setIdCliente(new BigInteger(rs.getString(2)));
					pedidoCompleto.getPedido().setDesCliente(rs.getString(3));
					pedidoCompleto.getPedido().setDataPedido(rs.getDate(4));
					pedidoCompleto.getPedido().setObservacao(rs.getString(5));
					pedidoCompleto.getPedido().setDesUsrCad(rs.getString(6));					
				}
				
				PedidoProduto pedidoProduto = new PedidoProduto();
				pedidoProduto.setIdPedidoProduto(new BigInteger(rs.getString(7)));
				pedidoProduto.setIdPedido(new BigInteger(rs.getString(8)));
				pedidoProduto.setDesProduto(rs.getString(9));
				pedidoProduto.setQtdSolicitada(rs.getInt(10));				
				
				pedidoCompleto.getListaPedidoProduto().add(pedidoProduto);
			}
			
			return pedidoCompleto;
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			if(conexao != null)
			{
				conexao.close();				
			}
		}
	}
}
