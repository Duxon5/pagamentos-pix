package com.banco.ficticio.pix.service;

import java.util.List;

import com.banco.ficticio.pix.dto.IntegracaoPixDTO;

public interface IntegracaoPixService {

	public List<IntegracaoPixDTO> buscarTodos() throws Exception;
	public void incluir(IntegracaoPixDTO integracaoPixDTO) throws Exception;
	
}
