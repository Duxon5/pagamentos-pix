package com.banco.ficticio.pix.sessao;

import org.springframework.web.socket.WebSocketSession;

public class SessaoWebSocket {

	private static WebSocketSession session;

	public static int getIdSession() {
		if(session == null) {
			return -1;
		}
		
		return session.hashCode();
	}
	
	public static WebSocketSession getSession() {
		return session;
	}

	public static void setSession(WebSocketSession _session) {
		boolean isSemSessao = session == null;
		boolean isMudouSessao = !isSemSessao && _session.hashCode() != session.hashCode();
		
		if(isSemSessao || isMudouSessao) {
			session = _session;
		}
	}
	
	public static void clearSession() {
		session = null;
	}
	
	public static boolean isSessionOpen() {
		return session != null;
	}
	
}
