package br.com.a2dm.spdmws.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.a2dm.spdmws.dao.PedidoDao;
import br.com.a2dm.spdmws.dao.factory.ConnectionFactory;
import br.com.a2dm.spdmws.entity.Pedido;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.PedidoProduto;
import br.com.a2dm.spdmws.entity.ProdutoCliente;
import br.com.a2dm.spdmws.entity.ProdutoParam;

public class PedidoDaoImpl implements PedidoDao
{
	private static PedidoDaoImpl instancia = null;
	
	private PedidoDaoImpl()
	{
		
	}
	
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
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			PedidoCompleto pedidoCompleto = null;
			
			String sql = " SELECT p.id_pedido, p.id_cliente, c.des_cliente, p.dat_pedido, p.obs_pedido,         "
					   + "    	  u.nome, pp.id_pedido_produto, pp.id_pedido, pd.des_produto, pp.qtd_solicitada "                                                      
	                   + " FROM ped.tb_pedido p												                    "
					   + " INNER JOIN ped.tb_pedido_produto pp on p.id_pedido = pp.id_pedido                    "
					   + " INNER JOIN ped.tb_cliente c on c.id_cliente = p.id_cliente                           "
					   + " INNER JOIN ped.tb_usuario u on u.id_usuario = p.id_usuario_cad                       "
					   + " INNER JOIN ped.tb_produto pd on pd.id_produto = pp.id_produto                        "
					   + " WHERE p.flg_ativo = 'S'                                                              "          
					   + "   AND pp.flg_ativo = 'S'                                                             "     
					   + "   AND p.id_cliente = ?                                                               "    
					   + "   AND p.dat_pedido = to_date(?, 'yyyy-MM-dd')                                        ";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setLong(1, idCliente.longValue());
			pst.setString(2, dataPedido);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				if (rs.isFirst())
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
	}
	
	@Override
	public List<ProdutoCliente> getListaProdutoByCliente(Long idCliente) throws Exception
	{
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			List<ProdutoCliente> listaProdutoCliente = new ArrayList<>();
			
			String sql = " SELECT cp.id_produto, p.des_produto                         "
					   + " FROM ped.tb_cliente_produto cp                              "
					   + " INNER JOIN ped.tb_produto p on cp.id_produto = p.id_produto "
					   + " WHERE cp.id_cliente = ?                                     "
					   + " AND cp.flg_ativo = 'S'                                      ";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setLong(1, idCliente.longValue());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				ProdutoCliente produtoCliente = new ProdutoCliente();
				produtoCliente.setIdProduto(new BigInteger(rs.getString(1)));
				produtoCliente.setDesProduto(rs.getString(2));
				
				listaProdutoCliente.add(produtoCliente);
			}
			return listaProdutoCliente;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}

	public Long processCadastrar(Long idCliente, Long idUsuario, Date data, String observacao, List<ProdutoParam> listaProdutosAdicionados) throws Exception 
	{
		Long idPedido = null;
		
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			idPedido = getSequence(conexao, "ped.sq_pedido");
			inserirPedido(idCliente, idUsuario, data, observacao, idPedido, conexao);
			inserirPedidoProduto(idPedido, idUsuario, listaProdutosAdicionados, conexao);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return idPedido;
	}

	public Long processAlterar(Long idPedido, Long idUsuario, Date data, String observacao, List<ProdutoParam> listaProdutosAdicionados) throws Exception 
	{
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			alterarPedido(idPedido, idUsuario, data, observacao, conexao);
			inativarPedidoProduto(idPedido, idUsuario, conexao);
			inserirPedidoProduto(idPedido, idUsuario, listaProdutosAdicionados, conexao);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return null;
	}

	public Long inativar(Long idPedido, Long idUsuario, Date data, String observacao, List<ProdutoParam> listaProdutosAdicionados) throws Exception 
	{
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return null;
	}
	
	private void inserirPedido(Long idCliente, Long idUsuario, Date data, String observacao, Long idPedido, Connection conexao) throws SQLException 
	{
		String sqlInsertPedido = " INSERT INTO ped.tb_pedido(id_pedido,      "
				               + "                           id_cliente,     "
				               + "                           dat_pedido,     "
				               + "                           flg_ativo,      "
				               + "                           dat_cadastro,   "
				               + "                           id_usuario_cad, "
				               + "                           obs_pedido)     "
				               + " VALUES (?, ?, ?, ?, ?, ?, ?)              ";
		
		java.util.Date utilDate = new java.util.Date();
		
		PreparedStatement pst = conexao.prepareStatement(sqlInsertPedido);
		pst.setLong(1, idPedido);
		pst.setLong(2, idCliente);
		pst.setDate(3, new java.sql.Date(data.getTime()));
		pst.setString(4, "S");
		pst.setDate(5, new java.sql.Date(utilDate.getTime()));
		pst.setLong(6, idUsuario);
		pst.setString(7, observacao);
		
		pst.executeUpdate();
	}
	
	private void inserirPedidoProduto(Long idPedido, Long idUsuario, List<ProdutoParam> listaProdutosAdicionados, Connection conexao) throws SQLException 
	{
		PreparedStatement pst;
		java.util.Date utilDate = new java.util.Date();
		
		if (listaProdutosAdicionados != null && listaProdutosAdicionados.size() > 0) {
			for (ProdutoParam element : listaProdutosAdicionados) {
				
				String sqlInsertPedidoProduto = " INSERT INTO ped.tb_pedido_produto(id_pedido_produto, "
												+ "                                 id_pedido,         "
												+ "                                 id_produto,        "
												+ "                                 qtd_solicitada,    "
												+ "                                 flg_ativo,         "
												+ "                                 dat_cadastro,      "
												+ "                                 id_usuario_cad)    "
												+ " VALUES (?, ?, ?, ?, ?, ?, ?)                       ";
				
				Long idPedidoproduto = getSequence(conexao, "ped.sq_pedido_produto");
				
				pst = conexao.prepareStatement(sqlInsertPedidoProduto);
				pst.setLong(1, idPedidoproduto);
				pst.setLong(2, idPedido);
				pst.setLong(3, element.getIdProduto());
				pst.setInt(4, element.getQtdSolicitada());
				pst.setString(5, "S");
				pst.setDate(6, new java.sql.Date(utilDate.getTime()));
				pst.setLong(7, idUsuario);
				
				pst.executeUpdate();
			}
		}
	}
	
	private void alterarPedido(Long idPedido, Long idUsuario, Date data, String observacao, Connection conexao) throws SQLException 
	{
		String sql = " UPDATE ped.tb_pedido SET dat_pedido = ?,     "
				   + "                          dat_alteracao = ?,  "
				   + "                          id_usuario_alt = ?, "
				   + "                          obs_pedido = ?      "
		           + " WHERE id_pedido = ?                          ";
		
		java.util.Date utilDate = new java.util.Date();
		
		PreparedStatement pst = conexao.prepareStatement(sql);
		pst.setDate(1, new java.sql.Date(data.getTime()));
		pst.setDate(2, new java.sql.Date(utilDate.getTime()));
		pst.setLong(3, idUsuario);
		pst.setString(4, observacao);
		pst.setLong(5, idPedido);
		
		pst.executeUpdate();
	}
	
	private void inativarPedido(Long idPedido, Long idUsuario, Date data, Connection conexao) throws SQLException 
	{
		String sql = " UPDATE ped.tb_pedido SET id_usuario_alt = ?, "
				   + "                          dat_alteracao = ?,  "
				   + "                          flg_ativo = 'N',    "
		           + " WHERE id_pedido = ?                          ";
		
		java.util.Date utilDate = new java.util.Date();
		
		PreparedStatement pst = conexao.prepareStatement(sql);
		pst.setLong(1, idUsuario);
		pst.setDate(2, new java.sql.Date(data.getTime()));
		
		pst.executeUpdate();
	}
	
	private void inativarPedidoProduto(Long idPedido, Long idUsuario, Connection conexao) throws SQLException 
	{
		PreparedStatement pst;
		java.util.Date utilDate = new java.util.Date();
		
		String sqlUpdatePedidoProduto = " UPDATE ped.tb_pedido_produto SET id_usuario_alt = ?, "
				                      + "                                  dat_alteracao = ?,  "
				                      + "                                  flg_ativo = 'N'     "
				                      + " WHERE idPedido = ?                                   ";
		
		pst = conexao.prepareStatement(sqlUpdatePedidoProduto);
		pst.setLong(1, idUsuario);
		pst.setDate(2, new java.sql.Date(utilDate.getTime()));
		pst.setLong(3, idPedido);
		
		pst.executeUpdate();
	}
	
	private long getSequence(Connection conexao, String sequence) throws SQLException 
	{
		String sqPedido = "select nextval('"+sequence+"')";
		PreparedStatement pst = conexao.prepareStatement(sqPedido);
		
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}
}