package com.banco.ficticio.pix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.ficticio.pix.dto.IntegracaoPixDTO;
import com.banco.ficticio.pix.service.IntegracaoPixService;

@CrossOrigin(origins = {"http://localhost:1234"})
@RestController
@RequestMapping("/pix")
public class IntegracaoPixController {

	@Autowired
	private IntegracaoPixService integracaoPixService;
	
	@GetMapping(value = "/buscarTodos", produces = "application/json")
	public ResponseEntity<?> buscarTodos() {
		
		List<IntegracaoPixDTO> integracoesPixDTO = null;
		
		try {
			integracoesPixDTO = integracaoPixService.buscarTodos();
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(integracoesPixDTO, HttpStatus.OK);
	}
	
}
