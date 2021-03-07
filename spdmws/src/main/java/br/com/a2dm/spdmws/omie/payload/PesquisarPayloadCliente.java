package br.com.a2dm.spdmws.omie.payload;

import java.math.BigInteger;

public class PesquisarPayloadCliente {

	private BigInteger pagina;
	private BigInteger registros_por_pagina;
	private String apenas_importado_api;
	private ClientesFiltroPayload clientesFiltro;

	public PesquisarPayloadCliente(BigInteger pagina, BigInteger registros_por_pagina, String apenas_importado_api,
			ClientesFiltroPayload clientesFiltro) {
		super();
		this.pagina = pagina;
		this.registros_por_pagina = registros_por_pagina;
		this.apenas_importado_api = apenas_importado_api;
		this.clientesFiltro = clientesFiltro;
	}

	public BigInteger getPagina() {
		return pagina;
	}

	public void setPagina(BigInteger pagina) {
		this.pagina = pagina;
	}

	public BigInteger getRegistros_por_pagina() {
		return registros_por_pagina;
	}

	public void setRegistros_por_pagina(BigInteger registros_por_pagina) {
		this.registros_por_pagina = registros_por_pagina;
	}

	public String getApenas_importado_api() {
		return apenas_importado_api;
	}

	public void setApenas_importado_api(String apenas_importado_api) {
		this.apenas_importado_api = apenas_importado_api;
	}

	public ClientesFiltroPayload getClientesFiltro() {
		return clientesFiltro;
	}

	public void setClientesFiltro(ClientesFiltroPayload clientesFiltro) {
		this.clientesFiltro = clientesFiltro;
	}
}
