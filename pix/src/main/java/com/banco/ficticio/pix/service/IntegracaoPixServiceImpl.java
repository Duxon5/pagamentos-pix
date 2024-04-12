package com.banco.ficticio.pix.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.banco.ficticio.pix.dao.IntegracaoPixDAO;
import com.banco.ficticio.pix.dto.IntegracaoPixDTO;
import com.banco.ficticio.pix.enums.TipoAcaoEnum;
import com.banco.ficticio.pix.model.IntegracaoPixModel;
import com.banco.ficticio.pix.model.PagamentoModel;
import com.banco.ficticio.pix.sessao.SessaoWebSocket;
import com.banco.ficticio.pix.util.Util;

import jakarta.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Service
public class IntegracaoPixServiceImpl implements IntegracaoPixService {

	@Autowired
	private IntegracaoPixDAO integracaoPixDAO;

	private ModelMapper map = new ModelMapper();

	@Override
	public void informarDDL(PagamentoModel pagamentoModel, TipoAcaoEnum tipoAcao) {

		IntegracaoPixDTO integracaoPixDTO = new IntegracaoPixDTO(pagamentoModel, tipoAcao);
		
		if(!SessaoWebSocket.isSessionOpen()) {
			System.out.println("Sessão fechada: incluirFalhaNaIntegracao");
			incluirFalhaNaIntegracao(integracaoPixDTO);
			return;
		}
		
		try {
			String json = Util.convertObjectToJson(integracaoPixDTO);
			SessaoWebSocket.getSession().sendMessage(new TextMessage(json));
			
			System.out.println(json);
			
		} catch (Exception e) {
			System.out.println("Caiu no catch: incluirFalhaNaIntegracao");
			incluirFalhaNaIntegracao(integracaoPixDTO);
		}
		
		informarDDLsAnterioresNaoIntegrados();
	}

	/**
	 * Informa os que não foram integrados, pois a conexão estava fechada ou deu erro na integração. 
	 */
	private void informarDDLsAnterioresNaoIntegrados() {
		List<IntegracaoPixModel> listaTentarNovamente = integracaoPixDAO.buscarTodos();
		
		for (IntegracaoPixModel integracaoPixModel : listaTentarNovamente) {
			try {
				IntegracaoPixDTO integracaoPixDTO = map.map(integracaoPixModel, IntegracaoPixDTO.class);
				String json = Util.convertObjectToJson(integracaoPixDTO);
				SessaoWebSocket.getSession().sendMessage(new TextMessage(json));
				
				integracaoPixDAO.deletar(integracaoPixModel);
			} catch (Exception e) { }
		}
	}

	@Override
	public void incluirFalhaNaIntegracao(IntegracaoPixDTO integracaoPixDTO) {

		IntegracaoPixModel integracaoPixModel = map.map(integracaoPixDTO, IntegracaoPixModel.class);
		
		if(integracaoPixModel.getId() == null) {
			integracaoPixDAO.incluir(integracaoPixModel);
		} else {
			integracaoPixDAO.atualizar(integracaoPixModel);
		}
	}

}
