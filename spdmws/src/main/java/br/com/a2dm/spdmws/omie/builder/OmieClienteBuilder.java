package br.com.a2dm.spdmws.omie.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import br.com.a2dm.spdm.entity.Cliente;
import br.com.a2dm.spdm.service.ClienteService;
import br.com.a2dm.spdmws.dto.ClienteDTO;
import br.com.a2dm.spdmws.omie.payload.ClientesFiltroPayload;
import br.com.a2dm.spdmws.omie.payload.PesquisarPayloadCliente;
import br.com.a2dm.spdmws.utils.JsonUtils;

public class OmieClienteBuilder {

	public OmieClienteBuilder() {
		
	}
	
	public PesquisarPayloadCliente buildPesquisarCliente(String nomeCliente) throws OmieBuilderException {
		try {
			return new PesquisarPayloadCliente(new BigInteger("1"), 
					                           new BigInteger("50"), 
					                           "N",
					                           new ClientesFiltroPayload(nomeCliente));
		} catch (Exception e) {
			throw new OmieBuilderException("Erro ao montar json para Pesquisar Pedido", e);
		}
	}

	public List<ClienteDTO> buildPesquisarClienteResponse(String json) {
		try {
			List<ClienteDTO> clientes = new ArrayList<>();
			JSONObject jsonObject = JsonUtils.parse(json);
			JSONArray clientesCadastroResumido = (JSONArray) jsonObject.getJSONArray("clientes_cadastro_resumido");
			
			for (int i = 0; i < clientesCadastroResumido.length(); i++) {
				JSONObject cliente = (JSONObject) clientesCadastroResumido.get(i);
				ClienteDTO clienteDTO = this.buildClienteDTO(cliente);
				if (clienteDTO != null) {
					clientes.add(clienteDTO);					
				}
			}
			return clientes;
		} catch (Exception e) {
			throw new OmieBuilderException(e);
		}
	}
	
	public ClienteDTO buildClienteDTO(JSONObject clienteOMIE) throws Exception {
		BigInteger idExternoOmie = new BigInteger(clienteOMIE.getString("codigo_cliente"));
		String nomeCliente = clienteOMIE.getString("nome_fantasia");
		
		Cliente cliente = obterClientePorIdExternoOmie(idExternoOmie);
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdExternoOmie(idExternoOmie);
		clienteDTO.setNomeCliente(nomeCliente);
		
		if (cliente != null) {
			clienteDTO.setCadastroIncompleto(false);
			clienteDTO.setIdCliente(cliente.getIdCliente());
			if (cliente.getIdTabelaPrecoOmie() != null) {
				clienteDTO.setIdTabelaPrecoOmie(cliente.getIdTabelaPrecoOmie());
			} else {
				clienteDTO.setCadastroIncompleto(true);
			}
		} else {
			clienteDTO.setCadastroIncompleto(true);
		}
		
		return clienteDTO;
		
	}

	private Cliente obterClientePorIdExternoOmie(BigInteger idExternoOmie) throws Exception {
		Cliente cliente = new Cliente();
		cliente.setIdExternoOmie(idExternoOmie);
		cliente = ClienteService.getInstancia().get(cliente, 0);
		return cliente;
	}
}
