package com.banco.ficticio.pix.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Util {

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
