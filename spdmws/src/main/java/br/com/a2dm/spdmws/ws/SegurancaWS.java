package br.com.a2dm.spdmws.ws;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.a2dm.brcmn.entity.Usuario;
import br.com.a2dm.brcmn.service.RecuperarSenhaService;
import br.com.a2dm.brcmn.service.UsuarioService;
import br.com.a2dm.brcmn.util.criptografia.CriptoMD5;
import br.com.a2dm.spdmws.builders.UsuarioBuilder;
import br.com.a2dm.spdmws.dto.AlteraSenhaDTO;
import br.com.a2dm.spdmws.dto.LoginDTO;
import br.com.a2dm.spdmws.dto.UsuarioDTO;
import br.com.a2dm.spdmws.exception.ApiException;
import br.com.a2dm.spdmws.exception.ExceptionUtils;

@Path("/seguranca")
public class SegurancaWS {
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioDTO login(LoginDTO loginDTO) throws ApiException {
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(loginDTO.getLogin().toUpperCase().trim());
			usuario.setSenha(CriptoMD5.stringHexa(loginDTO.getSenha().toUpperCase()));
			usuario.setFlgAtivo("S");
			usuario = UsuarioService.getInstancia().get(usuario, 0);

			if (usuario == null) {
				throw new ApiException(401, "Autenticação inválida");
			}
			
			return UsuarioBuilder.buildUsuarioDTO(usuario);

		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@POST
	@Path("/recuperarSenha")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioDTO recuperarSenha(UsuarioDTO usuarioDTO) throws ApiException {
		try {
			Usuario usuario = RecuperarSenhaService.getInstancia().gerarHash(usuarioDTO.getEmail());
			return UsuarioBuilder.buildUsuarioDTO(usuario);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}
	}

	@PUT
	@Path("/alterarSenha")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioDTO alterarSenha(AlteraSenhaDTO alteraSenhaDTO)
			throws ApiException {
		try {
			
			if(!alteraSenhaDTO.getSenha().equals(alteraSenhaDTO.getSenhaConfirmacao())) {
				throw new ApiException(400, "Senhas estão diferentes");
			}
			
			Usuario usuario = new Usuario();
			usuario.setIdUsuarioAlt(alteraSenhaDTO.getIdUsuario());
			usuario.setDataAlteracao(new Date());

			usuario = UsuarioService.getInstancia().alterarSenha(usuario, alteraSenhaDTO.getSenha());
			return UsuarioBuilder.buildUsuarioDTO(usuario);
		} catch (Exception e) {
			throw ExceptionUtils.handlerApiException(e);
		}	
	}
}