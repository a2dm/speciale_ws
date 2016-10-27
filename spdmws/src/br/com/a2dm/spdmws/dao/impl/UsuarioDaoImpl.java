package br.com.a2dm.spdmws.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.a2dm.spdmws.dao.UsuarioDao;
import br.com.a2dm.spdmws.dao.factory.ConnectionFactory;
import br.com.a2dm.spdmws.entity.Usuario;

public class UsuarioDaoImpl implements UsuarioDao
{
	private static UsuarioDaoImpl instancia = null;
	
	private UsuarioDaoImpl(){}
	
	public static UsuarioDaoImpl getInstancia()
	{
		if (instancia == null)
		{
			instancia = new UsuarioDaoImpl();
		}
		return instancia;
	}
	
	
	@Override
	public Usuario login(String login, String senha) throws Exception
	{
		Connection conexao = null;
		
		try
		{
			conexao = ConnectionFactory.getConnection();
			
			Usuario usuario = null;
			
			String sql = "select u.nome, u.login, u.id_usuario, u.id_cliente" +
						 "	from seg.tb_usuario u " +
						 " where upper(u.login) = ?  " +
						 "   and u.senha = ?  " +
						 "   and upper(u.flg_ativo) = 'S' "+
						 "   and u.id_cliente is not null ";
			
			PreparedStatement pst = conexao.prepareStatement(sql);
			//SETAR PARAMETROS
			pst.setString(1, login);
			pst.setString(2, senha);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next())
			{	
				usuario = new Usuario();
				
				usuario.setNome(rs.getString(1));
				usuario.setLogin(rs.getString(2));
				usuario.setIdUsuario(new BigInteger(rs.getString(3)));
				usuario.setIdCliente(new BigInteger(rs.getString(4)));
			}
			
			return usuario;
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
