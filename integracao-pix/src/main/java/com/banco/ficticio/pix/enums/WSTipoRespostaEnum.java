package com.banco.ficticio.pix.enums;

public enum WSTipoRespostaEnum {

	OK(0),
	ERRO(1);

	private Integer tipo;

	WSTipoRespostaEnum(int tipo) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
}
