package br.com.a2dm.spdmws.builders;

import br.com.a2dm.brcmn.entity.Usuario;
import br.com.a2dm.spdmws.dto.UsuarioDTO;

public class UsuarioBuilder {

	public static UsuarioDTO buildUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setCpf(usuario.getCpf());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setIdCliente(usuario.getIdCliente());
		usuarioDTO.setLogin(usuario.getLogin());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setTelefone(usuario.getTelefone());
		usuarioDTO.setIdUsuario(usuario.getIdUsuario());
		return usuarioDTO;
	}
}
