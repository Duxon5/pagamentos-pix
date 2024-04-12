package com.banco.ficticio.pix.enums;

public enum TipoAcaoEnum {

	INCLUSAO(0),
	ALTERACAO(1), 
	EXCLUSAO(2);

	private Integer tipo;

	TipoAcaoEnum(int tipo) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
}
