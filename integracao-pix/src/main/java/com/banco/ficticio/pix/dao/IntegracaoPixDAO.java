package com.banco.ficticio.pix.dao;

import java.util.List;

import com.banco.ficticio.pix.model.IntegracaoPixModel;

public interface IntegracaoPixDAO  {

	public void incluir(IntegracaoPixModel integracaoPixModel) throws Exception;
	public List<IntegracaoPixModel> buscarTodos() throws Exception;
	
}
