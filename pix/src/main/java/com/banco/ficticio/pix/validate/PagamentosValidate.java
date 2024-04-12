package com.banco.ficticio.pix.validate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.banco.ficticio.pix.dto.PagamentoDTO;
import com.banco.ficticio.pix.entity.ValidacaoEntity;
import com.banco.ficticio.pix.enums.RecorrenciaPagamentoEnum;
import com.banco.ficticio.pix.enums.StatusPagamentoEnum;
import com.banco.ficticio.pix.enums.TipoChaveEnum;
import com.banco.ficticio.pix.util.Util;

@Service
public class PagamentosValidate {

	public ValidacaoEntity incluir(PagamentoDTO pagamentoDTO) {

		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(pagamentoDTO.getId() != null) {
			// O DB que irá gerenciar o ID
			pagamentoDTO.setId(null);
		}
		
		StatusPagamentoEnum status = Util.dataPagamentoToStatus(pagamentoDTO.getDataPagamento());
		pagamentoDTO.setStatus(status);
		
		// Deve ser setado a data de agora
		pagamentoDTO.setDataInclusao(LocalDateTime.now());
		
		if(pagamentoDTO.getDataPagamento() == null) {
			validacaoEntity.addMensagem("O campo 'dataPagamento' está nulo ou não foi informado.");
		} else if(!Util.isDataHojeOuMaior(pagamentoDTO.getDataPagamento())) {
			validacaoEntity.addMensagem("O campo 'dataPagamento' está com data inferior a data de hoje. Deve ser hoje ou superior.");
		}
		
		if(pagamentoDTO.getValor() == null) {
			validacaoEntity.addMensagem("O campo 'valor' está nulo ou não foi informado.");
		} else if(new BigDecimal("0").equals(pagamentoDTO.getValor().stripTrailingZeros())) {
			validacaoEntity.addMensagem("O campo 'valor' não pode ser 0");
		}
		
		if(pagamentoDTO.getRecorrencia() == null) {
			pagamentoDTO.setRecorrencia(RecorrenciaPagamentoEnum.NENHUMA);
		}
		
		if(pagamentoDTO.getDestinoChavePix() == null) {
			validacaoEntity.addMensagem("O campo 'destinoChavePix' está nulo ou não foi informado.");
		} else {
			TipoChaveEnum tipoChave = Util.chaveToTipoChave(pagamentoDTO.getDestinoChavePix());
			if(tipoChave == TipoChaveEnum.INVALIDA) {
				validacaoEntity.addMensagem("O campo 'destinoChavePix' está com formato errado. Deve ser [CPF, EMAIL, TELEFONE ou ALEATORIA].");
			} else {
				pagamentoDTO.setDestinoTipoChave(tipoChave);
			}
		}
		
		return validacaoEntity;
	}

	public ValidacaoEntity buscarPorStatus(PagamentoDTO pagamentoDTO) {
		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(pagamentoDTO == null) {
			return validacaoEntity;
		}
		
		if(pagamentoDTO.getStatus() == StatusPagamentoEnum.CANCELADO) {
			validacaoEntity.addMensagem("Não é possível filtrar pelo 'status' de CANCELADO. Deve ser [EFETUADO, AGENDADO ou nulo para filtrar TODOS].");
		}
		
		return validacaoEntity;
	}

	public ValidacaoEntity atualizar(PagamentoDTO pagamentoDTO) {

		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(pagamentoDTO.getId() == null) {
			validacaoEntity.addMensagem("O campo 'id' está nulo ou não foi informado.");
		}
		
		StatusPagamentoEnum status = Util.dataPagamentoToStatus(pagamentoDTO.getDataPagamento());
		pagamentoDTO.setStatus(status);
		
		// Deve ser setado a data de agora
		pagamentoDTO.setDataInclusao(LocalDateTime.now());
		
		if(pagamentoDTO.getDataPagamento() == null) {
			validacaoEntity.addMensagem("O campo 'dataPagamento' está nulo ou não foi informado.");
		} else if(!Util.isDataHojeOuMaior(pagamentoDTO.getDataPagamento())) {
			validacaoEntity.addMensagem("O campo 'dataPagamento' está com data inferior a data de hoje. Deve ser hoje ou superior.");
		}
		
		if(pagamentoDTO.getValor() == null) {
			validacaoEntity.addMensagem("O campo 'valor' está nulo ou não foi informado.");
		} else if(new BigDecimal("0").equals(pagamentoDTO.getValor().stripTrailingZeros())) {
			validacaoEntity.addMensagem("O campo 'valor' não pode ser 0");
		}
		
		if(pagamentoDTO.getRecorrencia() == null) {
			pagamentoDTO.setRecorrencia(RecorrenciaPagamentoEnum.NENHUMA);
		}
		
		if(pagamentoDTO.getDestinoChavePix() == null) {
			validacaoEntity.addMensagem("O campo 'destinoChavePix' está nulo ou não foi informado.");
		} else {
			TipoChaveEnum tipoChave = Util.chaveToTipoChave(pagamentoDTO.getDestinoChavePix());
			if(tipoChave == TipoChaveEnum.INVALIDA) {
				validacaoEntity.addMensagem("O campo 'destinoChavePix' está com formato errado. Deve ser [CPF, EMAIL, TELEFONE ou ALEATORIA].");
			} else {
				pagamentoDTO.setDestinoTipoChave(tipoChave);
			}
		}
		
		return validacaoEntity;
	}

	public ValidacaoEntity atualizarValor(PagamentoDTO pagamentoDTO) {

		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(pagamentoDTO.getId() == null) {
			validacaoEntity.addMensagem("O campo 'id' está nulo ou não foi informado.");
		}
		
		if(pagamentoDTO.getValor() == null) {
			validacaoEntity.addMensagem("O campo 'valor' está nulo ou não foi informado.");
		} else if(new BigDecimal("0").equals(pagamentoDTO.getValor().stripTrailingZeros())) {
			validacaoEntity.addMensagem("O campo 'valor' não pode ser 0");
		}
		
		return validacaoEntity;
	}

}
