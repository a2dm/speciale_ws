package br.com.a2dm.spdmws.ws;

import java.math.BigInteger;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.brcmn.entity.Usuario;
import br.com.a2dm.brcmn.service.RecuperarSenhaService;
import br.com.a2dm.brcmn.service.UsuarioService;
import br.com.a2dm.brcmn.util.criptografia.CriptoMD5;
import br.com.a2dm.spdm.exception.ServiceException;
import br.com.a2dm.spdmws.utils.JsonUtils;

@Path("/LoginWS")
public class LoginWS
{
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario login(@FormParam("login") String login, @FormParam("senha") String senha) throws ServiceException{
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(login.toUpperCase().trim());
			usuario.setSenha(CriptoMD5.stringHexa(senha.toUpperCase()));
			usuario.setFlgAtivo("S");
			usuario = UsuarioService.getInstancia().get(usuario, 0);
				
			if(usuario == null) {
				throw new ServiceException("Cliente n�o encontrado");
			}
				
			return JsonUtils.serializeInstance(usuario);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@POST
	@Path("/recuperarSenha")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario recuperarSenha(@FormParam("email") String email) throws ServiceException{
		try {
			Usuario usuario = RecuperarSenhaService.getInstancia().gerarHash(email);
			return JsonUtils.serializeInstance(usuario);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@POST
	@Path("/alterarSenha")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario alterarSenha(@FormParam("idUsuario") BigInteger idUsuario,
			                 @FormParam("senha") String senha) throws ServiceException{
		try {
			Usuario usuario = new Usuario();
			usuario.setIdUsuarioAlt(idUsuario);
			usuario.setDataAlteracao(new Date());
			
			usuario = UsuarioService.getInstancia().alterarSenha(usuario, senha);
			
			return JsonUtils.serializeInstance(usuario);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}