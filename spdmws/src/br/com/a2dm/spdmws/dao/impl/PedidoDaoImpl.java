package br.com.a2dm.spdmws.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.a2dm.spdmws.dao.PedidoDao;
import br.com.a2dm.spdmws.dao.factory.ConnectionFactory;
import br.com.a2dm.spdmws.entity.Cliente;
import br.com.a2dm.spdmws.entity.Mensagem;
import br.com.a2dm.spdmws.entity.Pedido;
import br.com.a2dm.spdmws.entity.PedidoCompleto;
import br.com.a2dm.spdmws.entity.PedidoProduto;
import br.com.a2dm.spdmws.entity.Produto;
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
	
	private PedidoCompleto getPedido(Long idPedido) throws Exception {
		return getPedido(idPedido, null, null);
	}
	
	@Override
	public PedidoCompleto getPedido(Long idCliente, Date dataPedido) throws Exception {
		return getPedido(null, idCliente, dataPedido);
	}
	
	@Override
	public PedidoCompleto getPedido(Long idPedido, Long idCliente, Date dataPedido) throws Exception {
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			PedidoCompleto pedidoCompleto = null;
			
			String sql = " SELECT p.id_pedido, p.id_cliente, c.des_cliente, p.dat_pedido, p.obs_pedido,      "
					+ "    	  u.nome, pp.id_pedido_produto, pp.id_pedido, pd.des_produto, pp.qtd_solicitada, "
					+ "       pd.id_produto                                                                   "                                                      
					+ " FROM ped.tb_pedido p												                 "
					+ " INNER JOIN ped.tb_pedido_produto pp on p.id_pedido = pp.id_pedido                    "
					+ " INNER JOIN ped.tb_cliente c on c.id_cliente = p.id_cliente                           "
					+ " INNER JOIN ped.tb_usuario u on u.id_usuario = p.id_usuario_cad                       "
					+ " INNER JOIN ped.tb_produto pd on pd.id_produto = pp.id_produto                        "
					+ " WHERE p.flg_ativo = 'S'                                                              "          
					+ "   AND pp.flg_ativo = 'S'                                                             ";     
			
			if (idPedido != null) {
				sql += " AND p.id_pedido = ? ";
			}
			
			if (idCliente != null) {
				sql += " AND p.id_cliente = ? ";
			}
			
			if (dataPedido != null) {
				sql += " AND p.dat_pedido = to_date(?, 'yyyy-MM-dd') ";
			}
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			if (idPedido != null) {
				pst.setLong(1, idPedido);
			} else {
				pst.setLong(1, idCliente);
				pst.setString(2, new SimpleDateFormat("yyyy/MM/dd").format(dataPedido));
			}
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				if (rs.isFirst())
				{
					pedidoCompleto = new PedidoCompleto();
					
					pedidoCompleto.setPedido(new Pedido());
					pedidoCompleto.setListaPedidoProduto(new ArrayList<PedidoProduto>());
					
					pedidoCompleto.getPedido().setIdPedido(new Long(rs.getString(1)));
					pedidoCompleto.getPedido().setIdCliente(new Long(rs.getString(2)));
					pedidoCompleto.getPedido().setDesCliente(rs.getString(3));
					pedidoCompleto.getPedido().setDataPedido(rs.getDate(4));
					pedidoCompleto.getPedido().setObservacao(rs.getString(5));
					pedidoCompleto.getPedido().setDesUsrCad(rs.getString(6));					
				}
				
				PedidoProduto pedidoProduto = new PedidoProduto();
				pedidoProduto.setIdPedidoProduto(new BigInteger(rs.getString(7)));
				pedidoProduto.setIdPedido(new BigInteger(rs.getString(8)));
				pedidoProduto.setDesProduto(rs.getString(9));
				pedidoProduto.setName(pedidoProduto.getDesProduto());
				pedidoProduto.setQtdSolicitada(rs.getInt(10));
				pedidoProduto.setIdProduto(new BigInteger(rs.getString(11)));
				
				pedidoCompleto.getListaPedidoProduto().add(pedidoProduto);
			}
			return pedidoCompleto;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	public PedidoCompleto getPedidoById(Long idPedido) throws Exception {
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			PedidoCompleto pedidoCompleto = null;
			
			String sql = " SELECT p.id_pedido, p.id_cliente, c.des_cliente, p.dat_pedido, p.obs_pedido,         "
					   + "    	  u.nome, pp.id_pedido_produto, pp.id_pedido, pd.des_produto, pp.qtd_solicitada,"
					   + "        pd.id_produto                                                                 "                                                      
	                   + " FROM ped.tb_pedido p												                    "
					   + " INNER JOIN ped.tb_pedido_produto pp on p.id_pedido = pp.id_pedido                    "
					   + " INNER JOIN ped.tb_cliente c on c.id_cliente = p.id_cliente                           "
					   + " INNER JOIN ped.tb_usuario u on u.id_usuario = p.id_usuario_cad                       "
					   + " INNER JOIN ped.tb_produto pd on pd.id_produto = pp.id_produto                        "
					   + " WHERE p.id_pedido = ?																"
					   + "   AND p.flg_ativo = 'S'                                                              "          
					   + "   AND pp.flg_ativo = 'S'                                                             ";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setLong(1, idPedido);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				if (rs.isFirst())
				{
					pedidoCompleto = new PedidoCompleto();
					
					pedidoCompleto.setPedido(new Pedido());
					pedidoCompleto.setListaPedidoProduto(new ArrayList<PedidoProduto>());
					
					pedidoCompleto.getPedido().setIdPedido(new Long(rs.getString(1)));
					pedidoCompleto.getPedido().setIdCliente(new Long(rs.getString(2)));
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
				pedidoProduto.setIdProduto(new BigInteger(rs.getString(11)));
				
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
					   + " AND cp.flg_ativo = 'S'                                      "
					   + " ORDER BY p.des_produto                                      ";
			
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

	public Long process(Long idCliente, Long idUsuario, Long idPedido, Date data, String observacao, List<ProdutoParam> listaProdutosAdicionados) throws Exception 
	{
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			if (idPedido.longValue() == 0) {
				idPedido = processCadastrar(idCliente, idUsuario, data, observacao, listaProdutosAdicionados, conexao);
			} else {
				processAlterar(idPedido, idUsuario, data, observacao, listaProdutosAdicionados, conexao);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return idPedido;
	}
	
	private Long processCadastrar(Long idCliente, Long idUsuario, Date data, String observacao, List<ProdutoParam> listaProdutosAdicionados, Connection conexao) throws Exception 
	{
		Long idPedido = getSequence(conexao, "ped.sq_pedido");
		inserirPedido(idCliente, idUsuario, data, observacao, idPedido, conexao);
		inserirPedidoProduto(idPedido, idUsuario, listaProdutosAdicionados, conexao);
		return idPedido;
	}

	private void processAlterar(Long idPedido, Long idUsuario, Date data, String observacao, List<ProdutoParam> listaProdutosAdicionados, Connection conexao) throws Exception 
	{
		alterarPedido(idPedido, idUsuario, data, observacao, conexao);
		inativarPedidoProduto(idPedido, idUsuario, conexao);
		inserirPedidoProduto(idPedido, idUsuario, listaProdutosAdicionados, conexao);
	}
	
	private Long verificarAlteracao(List<ProdutoParam> listaProdutosAdicionados) {
		if (listaProdutosAdicionados != null && listaProdutosAdicionados.size() > 0) {
			return listaProdutosAdicionados.get(0).getIdPedido();
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
				pst.setInt(4, element.getQuantity());
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
	
	public Long inativar(Long idPedido, Long idUsuario) throws Exception 
	{
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			inativarPedido(idPedido, idUsuario, conexao);
			inativarPedidoProduto(idPedido, idUsuario, conexao);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return idPedido;
	}
	
	private void inativarPedido(Long idPedido, Long idUsuario, Connection conexao) throws Exception 
	{
		java.util.Date utilDate = new java.util.Date();
		
		String sql = " UPDATE ped.tb_pedido SET id_usuario_alt = ?, "
				   + "                          dat_alteracao = ?,  "
				   + "                          flg_ativo = 'N'    "
				   + " WHERE id_pedido = ?                          ";
		
		PreparedStatement pst = conexao.prepareStatement(sql);
		pst.setLong(1, idUsuario);
		pst.setDate(2, new java.sql.Date(utilDate.getTime()));
		pst.setLong(3, idPedido);
		
		pst.executeUpdate();
	}
	
	private void inativarPedidoProduto(Long idPedido, Long idUsuario, Connection conexao) throws SQLException 
	{
		java.util.Date utilDate = new java.util.Date();
		
		String sqlUpdatePedidoProduto = " UPDATE ped.tb_pedido_produto SET id_usuario_alt = ?, "
				                      + "                                  dat_alteracao = ?,  "
				                      + "                                  flg_ativo = 'N'     "
				                      + " WHERE id_pedido = ?                                  ";
		
		PreparedStatement pst = conexao.prepareStatement(sqlUpdatePedidoProduto);
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

	public ProdutoCliente getProduto(Long idProduto) throws Exception {
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			ProdutoCliente produtoCliente = new ProdutoCliente();
			String sql = " SELECT p.id_produto, p.des_produto "
					   + " FROM ped.tb_produto p              "
					   + " WHERE p.id_produto = ?             ";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setLong(1, idProduto.longValue());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				produtoCliente.setIdProduto(new BigInteger(rs.getString(1)));
				produtoCliente.setDesProduto(rs.getString(2));
			}
			return produtoCliente;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	public PedidoCompleto preparaAlterarPedido(Long idPedido) throws Exception {
		PedidoCompleto pedidoCompleto = null;
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			pedidoCompleto = getPedido(idPedido);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}

	public PedidoCompleto preparaAlterarUltimoPedido(Long idCliente) throws Exception {
		PedidoCompleto pedidoCompleto = null;
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			Pedido pedido = getUltimoPedido(idCliente, conexao);
			if (pedido != null) {
				pedidoCompleto = getPedido(pedido.getIdPedido());
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}

	public PedidoCompleto inativarUltimoPedido(Long idUsuario, Long idCliente) throws Exception {
		PedidoCompleto pedidoCompleto = null;
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			Pedido pedido = getUltimoPedido(idCliente, conexao);
			if (pedido != null) {
				pedidoCompleto = getPedido(pedido.getIdPedido());
				inativarPedido(pedido.getIdPedido(), idUsuario, conexao);
				inativarPedidoProduto(pedido.getIdPedido(), idUsuario, conexao);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	public PedidoCompleto inativarPedido(Long idUsuario, Long idPedido) throws Exception {
		PedidoCompleto pedidoCompleto = null;
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			pedidoCompleto = getPedido(idPedido);
			inativarPedido(idPedido, idUsuario, conexao);
			inativarPedidoProduto(idPedido, idUsuario, conexao);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedidoCompleto;
	}
	
	public Pedido getUltimoPedido(Long idCliente) throws Exception {
		Pedido pedido = null;
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			pedido = getUltimoPedido(idCliente, conexao);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return pedido;
	}
	
	public Pedido getUltimoPedido(Long idCliente, Connection conexao) throws SQLException {
		Pedido pedido = null;
		
		String sql = "SELECT max(id_pedido) "
				   + "FROM ped.tb_pedido    "
				   + "WHERE id_cliente = ?  ";
		
		PreparedStatement pst = conexao.prepareStatement(sql);
		pst.setLong(1, idCliente);
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next() && rs.getString(1) != null)
		{	
			pedido = new Pedido();
			pedido.setIdPedido(new Long(rs.getString(1)));
		}
		return pedido;
	}

	public Mensagem validar(Long idProduto, Integer quantidade) throws Exception {
		Mensagem mensagem = new Mensagem();
		Produto produto = null;
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			String sql = "SELECT id_produto, des_produto, qtd_lot_minimo, qtd_multiplo FROM ped.tb_produto WHERE id_produto = ?";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setLong(1, idProduto.longValue());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				produto = new Produto();
				produto.setIdProduto(new Long(rs.getString(1)));
				produto.setDesProduto(rs.getString(2));
				produto.setQtdLoteMinimo(rs.getInt(3));
				produto.setQtdMultiplo(rs.getInt(4));
			}
			
			if (produto.getQtdLoteMinimo().intValue() > quantidade)
			{
				mensagem.setDescricao("O Lote Mínimo do produto " + produto.getDesProduto() + " não foi atingida! Quantidade de Lote Mínimo: " + produto.getQtdLoteMinimo());
				return mensagem;
			}
			
			if (quantidade.intValue() % produto.getQtdMultiplo().intValue() != 0)
			{
				mensagem.setDescricao("A Quantidade do produto " + produto.getDesProduto() + " deve ser solicitada em múltiplo de "+ produto.getQtdMultiplo() +"!");
				return mensagem;
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return null;
	}

	public Mensagem validarData(Long idCliente, Long idPedido, Date dataPedido) throws Exception {
		Mensagem mensagem = new Mensagem();
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			Calendar c = Calendar.getInstance();
			
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			
			Date dataHoje = c.getTime();
			
			if (dataPedido.before(dataHoje))
			{
				mensagem.setDescricao("O campo Data do Pedido não pode ser menor que a Data Atual!");
				return mensagem;
			}
			
			if (idPedido.longValue() == 0) {
				// VERIFICAR SE JA EXISTE PEDIDO ATIVO PARA O CLIENTE NA DATA ESCOLHIDA
				String sql = "SELECT id_pedido FROM ped.tb_pedido WHERE flg_ativo = 'S' AND id_cliente = ?  AND dat_pedido = to_date(?, 'yyyy-MM-dd')";
				
				PreparedStatement pst = conexao.prepareStatement(sql);
				pst.setLong(1, idCliente.longValue());
				pst.setString(2, new SimpleDateFormat("yyyy/MM/dd").format(dataPedido));
				
				ResultSet rs = pst.executeQuery();
				
				while (rs.next())
				{	
					mensagem.setDescricao("Já existe um pedido aberto para o dia " + new SimpleDateFormat("dd/MM/yyyy").format(dataPedido));
					return mensagem;
				}
			}
			
			// VERIFICAR SE O PEDIDO ESTA DENTRO DO PRAZO DE PEDIDO
			
			String acao = "realizado";
			mensagem = validarDataLimite(acao, idCliente, dataPedido, conexao);
			if (mensagem != null) {
				return mensagem;
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return null;
	}
	
	public Mensagem validarInativar(Long idCliente, Date dataPedido) throws Exception {
		Mensagem mensagem = new Mensagem();
		try (Connection conexao = ConnectionFactory.getConnection())
		{
			String acao = "inativado";
			mensagem = validarDataLimite(acao, idCliente, dataPedido, conexao);
			if (mensagem != null) {
				return mensagem;
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return null;
	}

	private Mensagem validarDataLimite(String acao, Long idCliente, Date dataPedido, Connection conexao) throws ParseException, SQLException {
		Mensagem mensagem = new Mensagem();
		Cliente cliente = null;
		String sqlCliente = "SELECT id_cliente, hor_limite FROM ped.tb_cliente WHERE id_cliente = ?";
		
		PreparedStatement pst = conexao.prepareStatement(sqlCliente);
		pst.setLong(1, idCliente.longValue());
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next())
		{
			cliente = new Cliente();
			cliente.setIdCliente(new Long(rs.getString(1)));
			cliente.setHorLimite(rs.getString(2));
		}
		
		//DATA LIMITE
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");		
		Date data = sdf.parse(cliente.getHorLimite());
		Calendar cLim = Calendar.getInstance();
		cLim.setTime(data);
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dataPedido);
		c1.set(Calendar.HOUR_OF_DAY, cLim.get(Calendar.HOUR_OF_DAY));
		c1.set(Calendar.MINUTE, cLim.get(Calendar.MINUTE));
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		
		Date dataLimite = c1.getTime();
		
		Date dataAtual = new Date();
		
		if(dataAtual.after(dataLimite))
		{
			mensagem.setDescricao("O pedido não pode ser " + acao + ", pois a hora limite do pedido foi ultrapassada! Hora limite: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataLimite));
			return mensagem;
		}
		return null;
	}

	public PedidoCompleto novoPedido() {
		PedidoCompleto pedidoCompleto = new PedidoCompleto();
		pedidoCompleto.setPedido(new Pedido());
		pedidoCompleto.getPedido().setIdPedido(0L);
		pedidoCompleto.getPedido().setDataPedido(new Date());
		pedidoCompleto.getPedido().setObservacao("");
		pedidoCompleto.setListaPedidoProduto(new ArrayList<>());
		return pedidoCompleto;
	}
}