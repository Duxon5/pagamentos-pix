package com.banco.ficticio.pix.configuration;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banco.ficticio.pix.sessao.SessaoWebSocket;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

@ClientEndpoint
@Component
public class WebSocketClientEndpoint {

	Session userSession = null;
	
	@Autowired
	private WebSocketHandler handler;

	public WebSocketClientEndpoint() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			
			URI endpointURI = new URI("ws://"+SessaoWebSocket.getAddress()+":4321/pix/ws/connect");
//			URI endpointURI = new URI("ws://localhost:4321/pix/ws/connect");
			container.connectToServer(this, endpointURI);

			SessaoWebSocket.setClient(this);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao iniciar o WebSocketClientEndpoint: " + e.toString());
		}
	}

	@OnOpen
	public void onOpen(Session userSession) {
		this.userSession = userSession;
		
		SessaoWebSocket.setSession(userSession);
		System.out.println("Conectando a sessão ["+SessaoWebSocket.getIdSession()+"]");
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("Desconectando a sessão ["+SessaoWebSocket.getIdSession()+"]");
		this.userSession = null;
		SessaoWebSocket.clearSession();
	}

	@OnMessage
	public void onMessage(String message) {
		if (this.handler != null) {
			try {
				this.handler.handleMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);
	}

}