package com.banco.ficticio.pix.enums;

public enum TipoChaveEnum {

	CPF(0), 
	EMAIL(1), 
	TELEFONE(2),
	ALEATORIA(3),
	INVALIDA(999);

	private Integer tipo;

	TipoChaveEnum(int tipo) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
