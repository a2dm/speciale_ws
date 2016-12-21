package br.com.a2dm.spdmws.entity;

import java.io.Serializable;

/**
 * @author Carlos Diego
 * @since 07/08/2016
 */

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idProduto;

	private String desProduto;

	private Integer idReceita;

	private Integer qtdLoteMinimo;

	private Integer qtdMultiplo;

	private String flgAtivo;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDesProduto() {
		return desProduto;
	}

	public void setDesProduto(String desProduto) {
		this.desProduto = desProduto;
	}

	public Integer getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(Integer idReceita) {
		this.idReceita = idReceita;
	}

	public Integer getQtdLoteMinimo() {
		return qtdLoteMinimo;
	}

	public void setQtdLoteMinimo(Integer qtdLoteMinimo) {
		this.qtdLoteMinimo = qtdLoteMinimo;
	}

	public Integer getQtdMultiplo() {
		return qtdMultiplo;
	}

	public void setQtdMultiplo(Integer qtdMultiplo) {
		this.qtdMultiplo = qtdMultiplo;
	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}
}