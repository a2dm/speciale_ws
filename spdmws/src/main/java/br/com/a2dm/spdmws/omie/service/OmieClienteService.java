package br.com.a2dm.spdmws.omie.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import br.com.a2dm.spdm.entity.Cliente;
import br.com.a2dm.spdm.service.ClienteService;
import br.com.a2dm.spdmws.dto.ClienteDTO;
import br.com.a2dm.spdmws.omie.payload.ClienteWebhookPayload;
import br.com.a2dm.spdmws.omie.payload.TabelaPrecoPayload;
import br.com.a2dm.spdmws.omie.payload.TagPayload;
import br.com.a2dm.spdmws.omie.repository.OmieClientesRepository;

public class OmieClienteService {

	private static OmieClienteService instance;

	private OmieClienteService() {
	}

	public static OmieClienteService getInstance() {
		if (instance == null) {
			instance = new OmieClienteService();
		}
		return instance;
	}

	public List<ClienteDTO> pesquisarClientes(String nomeCliente) throws OmieServiceException {
		try {
			return OmieClientesRepository.getInstance().pesquisarClientes(nomeCliente);
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}

	public void processar(ClienteWebhookPayload clienteWebhookPayload) throws OmieServiceException {
		try {
			
			String nomeTabelaPreco = obterTabelaPreco(clienteWebhookPayload.getEvent().getTags());
			TabelaPrecoPayload tabelaPreco = OmieTabelaPrecoService.getInstance().obterTabelaPreco(nomeTabelaPreco);
			
			Cliente cliente = new Cliente();
			cliente.setIdExternoOmie(clienteWebhookPayload.getEvent().getCodigo_cliente_omie());

			cliente = ClienteService.getInstancia().get(cliente, 0);

			if (cliente == null) {
				cliente = new Cliente();
				cliente.setDesCliente(clienteWebhookPayload.getEvent().getRazao_social());

				cliente = ClienteService.getInstancia().get(cliente, 0);

				if (cliente == null) {
					inserirCliente(clienteWebhookPayload, tabelaPreco);
				} else {
					alterarCliente(clienteWebhookPayload, cliente, tabelaPreco);
				}
			} else {
				alterarCliente(clienteWebhookPayload, cliente, tabelaPreco);
			}
		} catch (Exception e) {
			throw new OmieServiceException(e);
		}
	}
	
	private String obterTabelaPreco(List<TagPayload> tags) {
		for (TagPayload tag: tags) {
			if (isTagTabelaPreco(tag)) {
				return tag.getTag();
			}
		}
		return null;
	}

	private void inserirCliente(ClienteWebhookPayload clienteWebhookPayload, TabelaPrecoPayload tabelaPreco) throws Exception {
		Cliente clienteInsert = new Cliente();
		clienteInsert.setDesCliente(clienteWebhookPayload.getEvent().getRazao_social());
		clienteInsert.setIdExternoOmie(clienteWebhookPayload.getEvent().getCodigo_cliente_omie());
		clienteInsert.setIdTabelaPrecoOmie(tabelaPreco.getnCodTabPreco());
		clienteInsert.setFlgAtivo("S");
		clienteInsert.setDatCadastro(new Date());
		clienteInsert.setIdUsuarioCad(new BigInteger("1"));
		ClienteService.getInstancia().inserir(clienteInsert);
	}

	private void alterarCliente(ClienteWebhookPayload clienteWebhookPayload, Cliente cliente, TabelaPrecoPayload tabelaPreco) throws Exception {
		cliente.setDesCliente(clienteWebhookPayload.getEvent().getRazao_social());
		cliente.setIdExternoOmie(clienteWebhookPayload.getEvent().getCodigo_cliente_omie());
		cliente.setIdTabelaPrecoOmie(tabelaPreco.getnCodTabPreco());
		if (isTopicExcluido(clienteWebhookPayload)) {
			cliente.setFlgAtivo("N");
		}
		ClienteService.getInstancia().alterar(cliente);
	}
	
	private boolean isTagTabelaPreco(TagPayload tag) {
		return tag.getTag().indexOf("TP") == 0;
	}

	private boolean isTopicExcluido(ClienteWebhookPayload clienteWebhookPayload) {
		return clienteWebhookPayload.getTopic().equalsIgnoreCase("ClienteFornecedor.Excluido");
	}
}
