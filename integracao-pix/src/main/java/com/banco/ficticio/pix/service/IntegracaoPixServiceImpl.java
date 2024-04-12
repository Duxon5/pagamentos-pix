package com.banco.ficticio.pix.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.ficticio.pix.dao.IntegracaoPixDAO;
import com.banco.ficticio.pix.dto.IntegracaoPixDTO;
import com.banco.ficticio.pix.model.IntegracaoPixModel;

import jakarta.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Service
public class IntegracaoPixServiceImpl implements IntegracaoPixService {

	@Autowired
	private IntegracaoPixDAO integracaoPixDAO;
	
	private ModelMapper map = new ModelMapper();
	
	@Override
	public void incluir(IntegracaoPixDTO integracaoPixDTO) throws Exception {
		// Limpa o ID, pois o BD que vai gerenciar.
		integracaoPixDTO.setId(null);
		
		IntegracaoPixModel integracaoPixModel = map.map(integracaoPixDTO, IntegracaoPixModel.class);
		integracaoPixDAO.incluir(integracaoPixModel);
	}
	
	@Override
	public List<IntegracaoPixDTO> buscarTodos() throws Exception {
		
		List<IntegracaoPixModel> integracoesPixModel = integracaoPixDAO.buscarTodos();

		List<IntegracaoPixDTO> integracoesPixDTO = new ArrayList<>();
		
		integracoesPixModel.forEach(it -> {
			IntegracaoPixDTO dto = map.map(it, IntegracaoPixDTO.class);
			integracoesPixDTO.add(dto);
		});
		
		return integracoesPixDTO;
	}

}
