package br.com.a2dm.spdmws.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class PedidoDTO {

	private BigInteger idCliente;
	private BigInteger idUsuario;
	private BigInteger idPedido;
	private Date dataPedido;
	private String observacao;
	private String flgAtivo;
	private List<ProdutoDTO> produtos;
	private UsuarioDTO usuarioCadastro;

	public BigInteger getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(BigInteger idCliente) {
		this.idCliente = idCliente;
	}

	public BigInteger getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigInteger getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(BigInteger idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public String getFlgAtivo() {
		return flgAtivo;
	}
	
	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	
	public UsuarioDTO getUsuarioCadastro() {
		return usuarioCadastro;
	}
	
	public void setUsuarioCadastro(UsuarioDTO usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

}
