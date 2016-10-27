package br.com.a2dm.spdmws.dao;

import br.com.a2dm.spdmws.entity.Usuario;

public interface UsuarioDao
{
	public Usuario login(String login, String senha) throws Exception;
}
