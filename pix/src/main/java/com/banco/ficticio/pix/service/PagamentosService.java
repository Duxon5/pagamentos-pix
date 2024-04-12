package com.banco.ficticio.pix.service;

import java.util.List;

import com.banco.ficticio.pix.dto.PagamentoDTO;
import com.banco.ficticio.pix.entity.ValidacaoEntity;
import com.banco.ficticio.pix.exception.PagamentoException;

public interface PagamentosService {

	public ValidacaoEntity incluir(PagamentoDTO pagamentoDTO) throws PagamentoException;
	public ValidacaoEntity atualizar(PagamentoDTO pagamentoDTO) throws PagamentoException;
	public ValidacaoEntity atualizarValor(PagamentoDTO pagamentoDTO) throws PagamentoException;
	public List<PagamentoDTO> buscarPorStatus(PagamentoDTO pagamentoDTO) throws PagamentoException;
	public ValidacaoEntity deletar(Long id) throws PagamentoException;
	
}
