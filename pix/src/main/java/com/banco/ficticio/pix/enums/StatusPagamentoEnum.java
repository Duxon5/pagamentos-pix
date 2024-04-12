package com.banco.ficticio.pix.enums;

public enum StatusPagamentoEnum {

	EFETUADO(0), 
	AGENDADO(1), 
	CANCELADO(2);

	private Integer status;

	StatusPagamentoEnum(int status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
