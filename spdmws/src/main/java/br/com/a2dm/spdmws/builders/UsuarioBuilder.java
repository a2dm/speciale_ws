package br.com.a2dm.spdmws.builders;

import br.com.a2dm.brcmn.entity.Usuario;
import br.com.a2dm.spdm.entity.Cliente;
import br.com.a2dm.spdm.service.ClienteService;
import br.com.a2dm.spdmws.dto.UsuarioDTO;

public class UsuarioBuilder {

	public static UsuarioDTO buildUsuarioDTO(Usuario usuario) throws Exception {
		
		Cliente cliente = new Cliente();
		
		if (usuario.getIdCliente() != null) {
			cliente.setIdCliente(usuario.getIdCliente());
			cliente = ClienteService.getInstancia().get(cliente, 0);
		}
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setCpf(usuario.getCpf());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setIdCliente(usuario.getIdCliente());
		usuarioDTO.setIdGrupo(usuario.getIdGrupo());
		usuarioDTO.setIdTabelaPrecoOmie(cliente.getIdTabelaPrecoOmie());
		usuarioDTO.setIdExternoOmie(cliente.getIdExternoOmie());
		usuarioDTO.setLogin(usuario.getLogin());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setTelefone(usuario.getTelefone());
		usuarioDTO.setIdUsuario(usuario.getIdUsuario());
		
		return usuarioDTO;
	}
}