package com.banco.ficticio.pix.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ValidacaoEntity {

    private List<String> mensagens;
    
	public ValidacaoEntity(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public ValidacaoEntity() {
	}

	public List<String> getMensagens() {
        return mensagens;
    }

	public void addMensagem(String mensagem) {
		if(this.mensagens == null) {
			mensagens = new ArrayList<>();
		}
		
		mensagens.add(mensagem);
	}

	@JsonIgnore
	public boolean isVazio() {
		return this.mensagens == null || this.mensagens.isEmpty();
	}
}
