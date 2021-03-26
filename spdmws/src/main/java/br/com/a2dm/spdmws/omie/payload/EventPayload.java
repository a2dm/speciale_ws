package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;
import java.util.List;

public class EventPayload {

	private BigInteger codigo_cliente_omie;
	private String nome_fantasia;
	private String obs_detalhadas;
	private String bairro;
	private String bloqueado;
	private String bloquear_faturamento;
	private String cep;
	private String cidade;
	private String cnae;
	private String cnpj_cpf;
	private String codigo_cliente_integracao;
	private String codigo_pais;
	private String complemento;
	private String contato;
	private String contribuinte;
	private String email;
	private String endereco;
	private String endereco_numero;
	private String estado;
	private String exterior;
	private String fax_ddd;
	private String fax_numero;
	private String homepage;
	private String inativo;
	private String inscricao_estadual;
	private String inscricao_municipal;
	private String inscricao_suframa;
	private String logradouro;
	private String nif;
	private String observacao;
	private String optante_simples_nacional;
	private String pessoa_fisica;
	private String produtor_rural;
	private String razao_social;
	private String recomendacao_atraso;
	private RecomendacoesPayload recomendacoes;
	private List<TagPayload> tags;
	private String telefone1_ddd;
	private String telefone1_numero;
	private String telefone2_ddd;
	private String telefone2_numero;
	private String tipo_atividade;
	private String valor_limite_credito;
	private DadosBancariosPayload dadosBancarios;

	public EventPayload() {
		super();
	}

	public EventPayload(BigInteger codigo_cliente_omie, String nome_fantasia, String obs_detalhadas, String bairro,
			String bloqueado, String bloquear_faturamento, String cep, String cidade, String cnae, String cnpj_cpf,
			String codigo_cliente_integracao, String codigo_pais, String complemento, String contato,
			String contribuinte, String email, String endereco, String endereco_numero, String estado, String exterior,
			String fax_ddd, String fax_numero, String homepage, String inativo, String inscricao_estadual,
			String inscricao_municipal, String inscricao_suframa, String logradouro, String nif, String observacao,
			String optante_simples_nacional, String pessoa_fisica, String produtor_rural, String razao_social,
			String recomendacao_atraso, RecomendacoesPayload recomendacoes, List<TagPayload> tags, String telefone1_ddd,
			String telefone1_numero, String telefone2_ddd, String telefone2_numero, String tipo_atividade,
			String valor_limite_credito, DadosBancariosPayload dadosBancarios) {
		super();
		this.codigo_cliente_omie = codigo_cliente_omie;
		this.nome_fantasia = nome_fantasia;
		this.obs_detalhadas = obs_detalhadas;
		this.bairro = bairro;
		this.bloqueado = bloqueado;
		this.bloquear_faturamento = bloquear_faturamento;
		this.cep = cep;
		this.cidade = cidade;
		this.cnae = cnae;
		this.cnpj_cpf = cnpj_cpf;
		this.codigo_cliente_integracao = codigo_cliente_integracao;
		this.codigo_pais = codigo_pais;
		this.complemento = complemento;
		this.contato = contato;
		this.contribuinte = contribuinte;
		this.email = email;
		this.endereco = endereco;
		this.endereco_numero = endereco_numero;
		this.estado = estado;
		this.exterior = exterior;
		this.fax_ddd = fax_ddd;
		this.fax_numero = fax_numero;
		this.homepage = homepage;
		this.inativo = inativo;
		this.inscricao_estadual = inscricao_estadual;
		this.inscricao_municipal = inscricao_municipal;
		this.inscricao_suframa = inscricao_suframa;
		this.logradouro = logradouro;
		this.nif = nif;
		this.observacao = observacao;
		this.optante_simples_nacional = optante_simples_nacional;
		this.pessoa_fisica = pessoa_fisica;
		this.produtor_rural = produtor_rural;
		this.razao_social = razao_social;
		this.recomendacao_atraso = recomendacao_atraso;
		this.recomendacoes = recomendacoes;
		this.tags = tags;
		this.telefone1_ddd = telefone1_ddd;
		this.telefone1_numero = telefone1_numero;
		this.telefone2_ddd = telefone2_ddd;
		this.telefone2_numero = telefone2_numero;
		this.tipo_atividade = tipo_atividade;
		this.valor_limite_credito = valor_limite_credito;
		this.dadosBancarios = dadosBancarios;
	}

	public BigInteger getCodigo_cliente_omie() {
		return codigo_cliente_omie;
	}

	public void setCodigo_cliente_omie(BigInteger codigo_cliente_omie) {
		this.codigo_cliente_omie = codigo_cliente_omie;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getObs_detalhadas() {
		return obs_detalhadas;
	}

	public void setObs_detalhadas(String obs_detalhadas) {
		this.obs_detalhadas = obs_detalhadas;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getBloquear_faturamento() {
		return bloquear_faturamento;
	}

	public void setBloquear_faturamento(String bloquear_faturamento) {
		this.bloquear_faturamento = bloquear_faturamento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getCnpj_cpf() {
		return cnpj_cpf;
	}

	public void setCnpj_cpf(String cnpj_cpf) {
		this.cnpj_cpf = cnpj_cpf;
	}

	public String getCodigo_cliente_integracao() {
		return codigo_cliente_integracao;
	}

	public void setCodigo_cliente_integracao(String codigo_cliente_integracao) {
		this.codigo_cliente_integracao = codigo_cliente_integracao;
	}

	public String getCodigo_pais() {
		return codigo_pais;
	}

	public void setCodigo_pais(String codigo_pais) {
		this.codigo_pais = codigo_pais;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(String contribuinte) {
		this.contribuinte = contribuinte;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco_numero() {
		return endereco_numero;
	}

	public void setEndereco_numero(String endereco_numero) {
		this.endereco_numero = endereco_numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public String getFax_ddd() {
		return fax_ddd;
	}

	public void setFax_ddd(String fax_ddd) {
		this.fax_ddd = fax_ddd;
	}

	public String getFax_numero() {
		return fax_numero;
	}

	public void setFax_numero(String fax_numero) {
		this.fax_numero = fax_numero;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	public String getInscricao_estadual() {
		return inscricao_estadual;
	}

	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}

	public String getInscricao_municipal() {
		return inscricao_municipal;
	}

	public void setInscricao_municipal(String inscricao_municipal) {
		this.inscricao_municipal = inscricao_municipal;
	}

	public String getInscricao_suframa() {
		return inscricao_suframa;
	}

	public void setInscricao_suframa(String inscricao_suframa) {
		this.inscricao_suframa = inscricao_suframa;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getOptante_simples_nacional() {
		return optante_simples_nacional;
	}

	public void setOptante_simples_nacional(String optante_simples_nacional) {
		this.optante_simples_nacional = optante_simples_nacional;
	}

	public String getPessoa_fisica() {
		return pessoa_fisica;
	}

	public void setPessoa_fisica(String pessoa_fisica) {
		this.pessoa_fisica = pessoa_fisica;
	}

	public String getProdutor_rural() {
		return produtor_rural;
	}

	public void setProdutor_rural(String produtor_rural) {
		this.produtor_rural = produtor_rural;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getRecomendacao_atraso() {
		return recomendacao_atraso;
	}

	public void setRecomendacao_atraso(String recomendacao_atraso) {
		this.recomendacao_atraso = recomendacao_atraso;
	}

	public RecomendacoesPayload getRecomendacoes() {
		return recomendacoes;
	}

	public void setRecomendacoes(RecomendacoesPayload recomendacoes) {
		this.recomendacoes = recomendacoes;
	}

	public List<TagPayload> getTags() {
		return tags;
	}

	public void setTags(List<TagPayload> tags) {
		this.tags = tags;
	}

	public String getTelefone1_ddd() {
		return telefone1_ddd;
	}

	public void setTelefone1_ddd(String telefone1_ddd) {
		this.telefone1_ddd = telefone1_ddd;
	}

	public String getTelefone1_numero() {
		return telefone1_numero;
	}

	public void setTelefone1_numero(String telefone1_numero) {
		this.telefone1_numero = telefone1_numero;
	}

	public String getTelefone2_ddd() {
		return telefone2_ddd;
	}

	public void setTelefone2_ddd(String telefone2_ddd) {
		this.telefone2_ddd = telefone2_ddd;
	}

	public String getTelefone2_numero() {
		return telefone2_numero;
	}

	public void setTelefone2_numero(String telefone2_numero) {
		this.telefone2_numero = telefone2_numero;
	}

	public String getTipo_atividade() {
		return tipo_atividade;
	}

	public void setTipo_atividade(String tipo_atividade) {
		this.tipo_atividade = tipo_atividade;
	}

	public String getValor_limite_credito() {
		return valor_limite_credito;
	}

	public void setValor_limite_credito(String valor_limite_credito) {
		this.valor_limite_credito = valor_limite_credito;
	}

	public DadosBancariosPayload getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancariosPayload dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	@Override
	public String toString() {
		return "EventPayload [codigo_cliente_omie=" + codigo_cliente_omie + ", nome_fantasia=" + nome_fantasia
				+ ", obs_detalhadas=" + obs_detalhadas + ", bairro=" + bairro + ", bloqueado=" + bloqueado
				+ ", bloquear_faturamento=" + bloquear_faturamento + ", cep=" + cep + ", cidade=" + cidade + ", cnae="
				+ cnae + ", cnpj_cpf=" + cnpj_cpf + ", codigo_cliente_integracao=" + codigo_cliente_integracao
				+ ", codigo_pais=" + codigo_pais + ", complemento=" + complemento + ", contato=" + contato
				+ ", contribuinte=" + contribuinte + ", email=" + email + ", endereco=" + endereco
				+ ", endereco_numero=" + endereco_numero + ", estado=" + estado + ", exterior=" + exterior
				+ ", fax_ddd=" + fax_ddd + ", fax_numero=" + fax_numero + ", homepage=" + homepage + ", inativo="
				+ inativo + ", inscricao_estadual=" + inscricao_estadual + ", inscricao_municipal="
				+ inscricao_municipal + ", inscricao_suframa=" + inscricao_suframa + ", logradouro=" + logradouro
				+ ", nif=" + nif + ", observacao=" + observacao + ", optante_simples_nacional="
				+ optante_simples_nacional + ", pessoa_fisica=" + pessoa_fisica + ", produtor_rural=" + produtor_rural
				+ ", razao_social=" + razao_social + ", recomendacao_atraso=" + recomendacao_atraso + ", recomendacoes="
				+ recomendacoes + ", tags=" + tags + ", telefone1_ddd=" + telefone1_ddd + ", telefone1_numero="
				+ telefone1_numero + ", telefone2_ddd=" + telefone2_ddd + ", telefone2_numero=" + telefone2_numero
				+ ", tipo_atividade=" + tipo_atividade + ", valor_limite_credito=" + valor_limite_credito
				+ ", dadosBancarios=" + dadosBancarios + "]";
	}

}
