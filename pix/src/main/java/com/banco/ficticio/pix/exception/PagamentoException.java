package com.banco.ficticio.pix.exception;

import com.banco.ficticio.pix.entity.ValidacaoEntity;

public class PagamentoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ValidacaoEntity validacao;

    public PagamentoException(ValidacaoEntity validacao) {
        super();
        this.validacao = validacao;
    }

    public PagamentoException(String message, ValidacaoEntity validacao) {
        super(message);
        this.validacao = validacao;
    }

    public PagamentoException(Throwable cause, ValidacaoEntity validacao) {
        super(cause);
        this.validacao = validacao;
    }

    public PagamentoException(String message, Throwable cause, ValidacaoEntity validacao) {
        super(message, cause);
        this.validacao = validacao;
    }

    public ValidacaoEntity getValidacao() {
        return validacao;
    }

    public void setValidacao(ValidacaoEntity validacao) {
        this.validacao = validacao;
    }

}