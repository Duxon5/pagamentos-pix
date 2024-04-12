package com.banco.ficticio.pix.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banco.ficticio.pix.dto.IntegracaoPixDTO;
import com.banco.ficticio.pix.dto.RespostaIntegracaoPixDTO;
import com.banco.ficticio.pix.service.IntegracaoPixService;
import com.banco.ficticio.pix.sessao.SessaoWebSocket;
import com.banco.ficticio.pix.util.Util;


@Component	
public class WebSocketHandler {
	
	@Autowired
	private IntegracaoPixService integracaoPixService;
	
	public void handleMessage(String messagem) throws IOException {
		tratarIntegracao(messagem);
	}
	
	private void tratarIntegracao(String mensagemRecebida) throws IOException {
		IntegracaoPixDTO integracaoPixDTO = Util.convertJsonToObject(mensagemRecebida, IntegracaoPixDTO.class);

		if(integracaoPixDTO == null) {
			System.out.println("Falha CRÍTICA na integração!");
			
			RespostaIntegracaoPixDTO respostaIntegracaoPixDTO = new RespostaIntegracaoPixDTO();
			respostaIntegracaoPixDTO.erro(integracaoPixDTO);

			String json = Util.convertObjectToJson(respostaIntegracaoPixDTO);
			
			SessaoWebSocket.getClient().sendMessage(json);
			return;
		}

		try {
			integracaoPixService.incluir(integracaoPixDTO);
			
			System.out.println("Integrou com sucesso!");
			RespostaIntegracaoPixDTO respostaIntegracaoPixDTO = new RespostaIntegracaoPixDTO();
			respostaIntegracaoPixDTO.ok();

			String json = Util.convertObjectToJson(respostaIntegracaoPixDTO);
			
			SessaoWebSocket.getClient().sendMessage(json);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Falha na integração!");
			
			RespostaIntegracaoPixDTO respostaIntegracaoPixDTO = new RespostaIntegracaoPixDTO();
			respostaIntegracaoPixDTO.erro(integracaoPixDTO);

			String json = Util.convertObjectToJson(respostaIntegracaoPixDTO);
			
			SessaoWebSocket.getClient().sendMessage(json);
		}
		
	}
	
}