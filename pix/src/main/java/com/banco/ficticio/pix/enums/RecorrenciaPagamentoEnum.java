package com.banco.ficticio.pix.enums;

public enum RecorrenciaPagamentoEnum {

	NENHUMA(0),
	SEMANAL(1), 
	MENSAL(2), 
	TRIMESTRAL(3),
	SEMESTRAL(4);

	private Integer recorrencia;

	RecorrenciaPagamentoEnum(int recorrencia) {
		this.recorrencia = recorrencia;
	}

	public Integer getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(Integer recorrencia) {
		this.recorrencia = recorrencia;
	}

}
