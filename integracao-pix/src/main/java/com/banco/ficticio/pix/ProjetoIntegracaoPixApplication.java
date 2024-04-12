package com.banco.ficticio.pix;

import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.banco.ficticio.pix.sessao.SessaoWebSocket;

@SpringBootApplication
public class ProjetoIntegracaoPixApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		setArgs(args);
		SpringApplication.run(ProjetoIntegracaoPixApplication.class, args);
	}

	private static void setArgs(String[] args) {
		String address = null;

		if(args.length >= 1) {
			address = args[0];
			System.out.println("ARGS: " + address);
		} else {
			System.out.println("NAO HA ARGS");
		}
		
		SessaoWebSocket.setAddress(address);
	}

}
