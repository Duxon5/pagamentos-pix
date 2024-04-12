package com.banco.ficticio.pix.sessao;

import com.banco.ficticio.pix.configuration.WebSocketClientEndpoint;

import jakarta.websocket.Session;

public class SessaoWebSocket {

	private static String address;
	private static Session session;
	private static WebSocketClientEndpoint client;

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String _address) {
		if(_address == null) {
			_address = "localhost";
		} else {
			address = _address;
		}
	}

	public static int getIdSession() {
		if(session == null) {
			return -1;
		}
		
		return session.hashCode();
	}
	
	public static Session getSession() {
		return session;
	}

	public static void setSession(Session _session) {
		boolean isSemSessao = session == null;
		boolean isMudouSessao = !isSemSessao && _session.hashCode() != session.hashCode();
		
		if(isSemSessao || isMudouSessao) {
			session = _session;
		}
	}
	
	public static void clearSession() {
		session = null;
	}

	public static WebSocketClientEndpoint getClient() {
		return client;
	}
	
	public static void setClient(WebSocketClientEndpoint _client) {
		client = _client;
	}
	
}
