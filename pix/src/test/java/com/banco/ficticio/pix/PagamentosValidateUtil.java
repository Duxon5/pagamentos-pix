package com.banco.ficticio.pix;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.banco.ficticio.pix.dto.PagamentoDTO;
import com.banco.ficticio.pix.entity.ValidacaoEntity;
import com.banco.ficticio.pix.validate.PagamentosValidate;

public class PagamentosValidateUtil {

	private PagamentosValidate pagamentosValidate = new PagamentosValidate();
	
	@Test
	public void incluirDadosOkTest() {

		ValidacaoEntity validacaoEntityExpected = new ValidacaoEntity();
		
		PagamentoDTO pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setDataPagamento(LocalDateTime.now());
		pagamentoDTO.setValor(new BigDecimal("100.50"));
		pagamentoDTO.setDescricao("conta de agua");
		pagamentoDTO.setDestinoChavePix("1140028922");
		
		ValidacaoEntity validacaoEntityIncluir = pagamentosValidate.incluir(pagamentoDTO);
		
		Assert.assertEquals(validacaoEntityExpected.getMensagens(), validacaoEntityIncluir.getMensagens());
	}

	@Test
	public void incluirFaltandoDataPagamentoTest() {

		ValidacaoEntity validacaoEntityExpected = new ValidacaoEntity();
		validacaoEntityExpected.addMensagem("O campo 'dataPagamento' está nulo ou não foi informado.");
		
		PagamentoDTO pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setValor(new BigDecimal("100.50"));
		pagamentoDTO.setDescricao("conta de agua");
		pagamentoDTO.setDestinoChavePix("1140028922");
		
		ValidacaoEntity validacaoEntityIncluir = pagamentosValidate.incluir(pagamentoDTO);
		
		Assert.assertEquals(validacaoEntityExpected.getMensagens(), validacaoEntityIncluir.getMensagens());
	}
	
}
