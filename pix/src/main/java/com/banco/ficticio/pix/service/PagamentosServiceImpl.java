package com.banco.ficticio.pix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.ficticio.pix.dao.PagamentosDAO;
import com.banco.ficticio.pix.dto.PagamentoDTO;
import com.banco.ficticio.pix.entity.ValidacaoEntity;
import com.banco.ficticio.pix.enums.TipoAcaoEnum;
import com.banco.ficticio.pix.exception.PagamentoException;
import com.banco.ficticio.pix.model.PagamentoModel;
import com.banco.ficticio.pix.validate.PagamentosValidate;

import jakarta.transaction.Transactional;

@Transactional(rollbackOn=PagamentoException.class)
@Service
public class PagamentosServiceImpl implements PagamentosService {

	@Autowired
	private PagamentosDAO pagamentosDAO;
	
	@Autowired
	private PagamentosValidate pagamentosValidar;

	@Autowired
	private IntegracaoPixService integracaoPixService;
	
	private ModelMapper map = new ModelMapper();
	
	@Override
	public ValidacaoEntity incluir(PagamentoDTO pagamentoDTO) throws PagamentoException {
		
		ValidacaoEntity validacaoEntity = pagamentosValidar.incluir(pagamentoDTO);
		if(!validacaoEntity.isVazio()) {
			throw new PagamentoException(validacaoEntity);
		}
		
		validacaoEntity = new ValidacaoEntity();

		PagamentoModel pagamentoModel = map.map(pagamentoDTO, PagamentoModel.class);
		
		// Busca antes de incluir
		List<PagamentoModel> pagamentosModel = pagamentosDAO.buscarPorCondicoesIguais(pagamentoModel);
		
		pagamentosDAO.incluir(pagamentoModel);
		
		if(pagamentosModel.isEmpty()) {
			validacaoEntity.addMensagem("Inclusão com sucesso!");
		} else {
			validacaoEntity.addMensagem("Inclusão com sucesso! [ALERTA! Já havia um pagamento com as mesmas condições]");
		}
		
		// Informa os outros sistemas que houve um novo DDL
		integracaoPixService.informarDDL(pagamentoModel, TipoAcaoEnum.INCLUSAO);
		
		return validacaoEntity;
	}
	
	@Override
	public ValidacaoEntity atualizar(PagamentoDTO pagamentoDTO) throws PagamentoException {
		
		ValidacaoEntity validacaoEntity = pagamentosValidar.atualizar(pagamentoDTO);
		if(!validacaoEntity.isVazio()) {
			throw new PagamentoException(validacaoEntity);
		}
		
		Optional<PagamentoModel> pagamentoModelAtual = pagamentosDAO.buscarPorId(pagamentoDTO.getId());
		
		if(!pagamentoModelAtual.isPresent()) {
			validacaoEntity.addMensagem("Não há pagamentos com o id informado!");
			return validacaoEntity;
		}

		PagamentoModel pagamentoModel = map.map(pagamentoDTO, PagamentoModel.class);
		
		pagamentosDAO.atualizar(pagamentoModel);
		
		validacaoEntity.addMensagem("Atualização com sucesso!");
		
		// Informa os outros sistemas que houve um novo DDL
		integracaoPixService.informarDDL(pagamentoModel, TipoAcaoEnum.ALTERACAO);
		
		return validacaoEntity;
	}
	
	@Override
	public ValidacaoEntity atualizarValor(PagamentoDTO pagamentoDTO) throws PagamentoException {
		
		ValidacaoEntity validacaoEntity = pagamentosValidar.atualizarValor(pagamentoDTO);
		if(!validacaoEntity.isVazio()) {
			throw new PagamentoException(validacaoEntity);
		}
		
		Optional<PagamentoModel> pagamentoModelAtual = pagamentosDAO.buscarPorId(pagamentoDTO.getId());
		
		if(!pagamentoModelAtual.isPresent()) {
			validacaoEntity.addMensagem("Não há pagamentos com o id informado!");
			return validacaoEntity;
		}

		pagamentoModelAtual.get().setValor(pagamentoDTO.getValor());
		
		pagamentosDAO.atualizarValor(pagamentoModelAtual.get());
		
		validacaoEntity.addMensagem("Atualização com sucesso!");
		
		// Informa os outros sistemas que houve um novo DDL
		integracaoPixService.informarDDL(pagamentoModelAtual.get(), TipoAcaoEnum.ALTERACAO);
		
		return validacaoEntity;
	}
	
	@Override
	public List<PagamentoDTO> buscarPorStatus(PagamentoDTO pagamentoDTO) throws PagamentoException {
		
		ValidacaoEntity validacaoEntity = pagamentosValidar.buscarPorStatus(pagamentoDTO);
		if(!validacaoEntity.isVazio()) {
			throw new PagamentoException(validacaoEntity);
		}
		
		if(pagamentoDTO == null) {
			pagamentoDTO = new PagamentoDTO();
		}
		
		PagamentoModel pagamentoModel = map.map(pagamentoDTO, PagamentoModel.class);
		
		List<PagamentoModel> pagamentosModel = pagamentosDAO.buscarPorStatus(pagamentoModel);

		List<PagamentoDTO> pagamentosDTO = new ArrayList<>();
		
		pagamentosModel.forEach(it -> {
			PagamentoDTO dto = map.map(it, PagamentoDTO.class);
			pagamentosDTO.add(dto);
		});
		
		return pagamentosDTO;
	}

	@Override
	public ValidacaoEntity deletar(Long id) throws PagamentoException {
		
		ValidacaoEntity validacaoEntity = new ValidacaoEntity();

		Optional<PagamentoModel> pagamentoModel = pagamentosDAO.buscarPorId(id);
		
		if(!pagamentoModel.isPresent()) {
			validacaoEntity.addMensagem("Não há pagamentos com o id informado!");
			return validacaoEntity;
		}
		
		pagamentosDAO.deletar(pagamentoModel.get());
		
		validacaoEntity.addMensagem("Deletado com sucesso!");

		// Informa os outros sistemas que houve um novo DDL
		integracaoPixService.informarDDL(pagamentoModel.get(), TipoAcaoEnum.EXCLUSAO);
		
		return validacaoEntity;
	}

}
