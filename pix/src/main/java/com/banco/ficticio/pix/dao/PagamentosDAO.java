package com.banco.ficticio.pix.dao;

import java.util.List;
import java.util.Optional;

import com.banco.ficticio.pix.exception.PagamentoException;
import com.banco.ficticio.pix.model.PagamentoModel;

public interface PagamentosDAO  {

	public void incluir(PagamentoModel pagamentoModel) throws PagamentoException;
	public void atualizar(PagamentoModel pagamentoModel) throws PagamentoException;
	public void atualizarValor(PagamentoModel pagamentoModel) throws PagamentoException;
	public void deletar(PagamentoModel pagamentoModel) throws PagamentoException;
	public List<PagamentoModel> buscarPorStatus(PagamentoModel pagamentoModel) throws PagamentoException;
	public List<PagamentoModel> buscarPorCondicoesIguais(PagamentoModel pagamentoModel) throws PagamentoException;
	public Optional<PagamentoModel> buscarPorId(Long id) throws PagamentoException;
}
