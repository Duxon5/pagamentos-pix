package com.banco.ficticio.pix.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.banco.ficticio.pix.dto.IntegracaoPixDTO;
import com.banco.ficticio.pix.dto.RespostaIntegracaoPixDTO;
import com.banco.ficticio.pix.enums.WSTipoRespostaEnum;
import com.banco.ficticio.pix.service.IntegracaoPixService;
import com.banco.ficticio.pix.sessao.SessaoWebSocket;
import com.banco.ficticio.pix.util.Util;


@Component
public class WebSocketHandler extends TextWebSocketHandler {

	@Autowired
	private IntegracaoPixService integracaoPixService;
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
		String mensagemRecebida = (String) message.getPayload();
		tratarResposta(mensagemRecebida);
	}

	private void tratarResposta(String mensagemRecebida) throws IOException {
		RespostaIntegracaoPixDTO respostaIntegracaoPixDTO = Util.convertJsonToObject(mensagemRecebida, RespostaIntegracaoPixDTO.class);

		if(respostaIntegracaoPixDTO == null)
			return;
		
		if(respostaIntegracaoPixDTO.getTipoResposta() == WSTipoRespostaEnum.OK) {
			System.out.println("Integrou com sucesso!");
			return;
		}
		
		IntegracaoPixDTO integracaoPixDTO = respostaIntegracaoPixDTO.getIntegracaoPixDTO();

		if (integracaoPixDTO == null) {
			System.out.println("Falha CRÍTICA na integração! Mandou sem objeto para inserir no BD.");
		} else {
			System.out.println("Falha na integração! Inserindo no BD para envio posterior.");
			integracaoPixService.incluirFalhaNaIntegracao(integracaoPixDTO);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		SessaoWebSocket.setSession(session);
		
		System.out.println("Conectando a sessão ["+SessaoWebSocket.getIdSession()+"]");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("Desconectando a sessão ["+SessaoWebSocket.getIdSession()+"]");
		SessaoWebSocket.clearSession();
	}
	
}