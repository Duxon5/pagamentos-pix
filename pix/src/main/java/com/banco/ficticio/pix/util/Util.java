package com.banco.ficticio.pix.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.banco.ficticio.pix.enums.StatusPagamentoEnum;
import com.banco.ficticio.pix.enums.TipoChaveEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Util {

	public static String nullToString(Object obj) {
		String retorno = "";
		
		if(obj != null) {
			retorno = String.valueOf(obj);
		}
		
		return retorno;
	}

	public static TipoChaveEnum chaveToTipoChave(String destinoChavePix) {
		try {
		    if (isChaveCPF(destinoChavePix)) {
		        return TipoChaveEnum.CPF;
		    } else if (isChaveEmail(destinoChavePix)) {
		        return TipoChaveEnum.EMAIL;
		    } else if (isChaveTelefone(destinoChavePix)) {
		        return TipoChaveEnum.TELEFONE;
		    } else if (isChaveAleatoria(destinoChavePix)) {
		        return TipoChaveEnum.ALEATORIA;
		    } else {
		        return TipoChaveEnum.INVALIDA;
		    }
		} catch (Exception ex) {
			return TipoChaveEnum.INVALIDA;
		}
	}

	public static boolean isChaveCPF(String cpf) throws Exception {

		// Verifica se o CPF tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verifica se todos os dígitos são números
		for (char c : cpf.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		// Verifica se o CPF é válido
		int soma = 0;
		int peso = 10;
		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * peso;
			peso--;
		}

		int resto = soma % 11;
		int digito1 = resto < 2 ? 0 : 11 - resto;

		soma = 0;
		peso = 11;
		for (int i = 0; i < 10; i++) {
			soma += (cpf.charAt(i) - '0') * peso;
			peso--;
		}

		resto = soma % 11;
		int digito2 = resto < 2 ? 0 : 11 - resto;

		return digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0');

	}

	private static boolean isChaveEmail(String email) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
	}

	private static boolean isChaveTelefone(String telefone) throws Exception {
		// Verifica se a string possui 10 ou 11 dígitos
	    if (telefone.length() != 10 && telefone.length() != 11) {
	        return false;
	    }

	    // Verifica se todos os dígitos são números
	    for (char c : telefone.toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    // Verifica se o DDD está entre 11 e 99
	    int ddd = Integer.parseInt(telefone.substring(0, 2));
	    if (ddd < 11 || ddd > 99) {
	        return false;
	    }

	    return true;
	}

	private static boolean isChaveAleatoria(String aleatoria) throws Exception  {
		try {
	        UUID.fromString(aleatoria);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}
	
	public static boolean isDataHojeOuMaior(LocalDateTime data) {
	  LocalDateTime hojeInicioDia = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);

	  return data.isAfter(hojeInicioDia) || data.isEqual(hojeInicioDia);
	}

	public static StatusPagamentoEnum dataPagamentoToStatus(LocalDateTime dataPagamento) {
		StatusPagamentoEnum status = null;
		
		if(dataPagamento != null) {
			if(isDataHoje(dataPagamento)) {
				status = StatusPagamentoEnum.EFETUADO;
			} else if(isDataFutura(dataPagamento)) {
				status = StatusPagamentoEnum.AGENDADO;
			}
		}
		
		return status;
	}

	private static boolean isDataHoje(LocalDateTime dateTime) {
		LocalDate hoje = LocalDate.now();
		return dateTime.toLocalDate().equals(hoje);
	}
	
	public static boolean isDataFutura(LocalDateTime dateTime) {
		LocalDate hoje = LocalDate.now();
		return dateTime.toLocalDate().isAfter(hoje);
	}
	
	public static String convertObjectToJson(Object object) {
	    String json = null;
	    
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.registerModule(new JavaTimeModule());
		    
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	    return json;
	}

	public static <T> T convertJsonToObject(String json, Class<T> targetClass) {
		T object = null;
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.registerModule(new JavaTimeModule());
			object = objectMapper.readValue(json, targetClass);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
