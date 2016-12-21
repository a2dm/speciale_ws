package br.com.a2dm.spdmws.ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.spdmws.dao.impl.UsuarioDaoImpl;
import br.com.a2dm.spdmws.entity.Usuario;

@Path("/LoginWS")
public class LoginWS
{
	@POST
	@Path("/login")	
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario login(@FormParam("login") String login, @FormParam("senha") String senha)
	{
		Usuario usuario = null;
		
		try
		{
			usuario = UsuarioDaoImpl.getInstancia().login(login, senha);
			
		}
		catch (Exception ex)
		{
			ex.getMessage();
		}
		return usuario;
	}
	
	@GET
	@Path("/teste/{login}/{senha}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario teste(@PathParam("login") String login, @PathParam("senha") String senha)
	{
		Usuario usuario = null;
		
		try
		{
			usuario = UsuarioDaoImpl.getInstancia().login(login, senha);
			
		}
		catch (Exception ex)
		{
			ex.getMessage();
		}
		return usuario;
	}
}