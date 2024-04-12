package com.banco.ficticio.pix.dao;

import java.util.List;

import com.banco.ficticio.pix.model.IntegracaoPixModel;

public interface IntegracaoPixDAO  {

	public void incluir(IntegracaoPixModel integracaoPixModel);
	public void atualizar(IntegracaoPixModel integracaoPixModel);
	public List<IntegracaoPixModel> buscarTodos();
	public void deletar(IntegracaoPixModel integracaoPixModel);

}
