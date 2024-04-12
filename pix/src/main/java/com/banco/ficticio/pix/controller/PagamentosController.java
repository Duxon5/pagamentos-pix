package com.banco.ficticio.pix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.ficticio.pix.dto.PagamentoDTO;
import com.banco.ficticio.pix.entity.ValidacaoEntity;
import com.banco.ficticio.pix.exception.PagamentoException;
import com.banco.ficticio.pix.service.PagamentosService;

@CrossOrigin(origins = {"http://localhost:4321"})
@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {

	@Autowired
	private PagamentosService pagamentosService;
	
	@PostMapping(value = "/incluir", produces = "application/json")
	public ResponseEntity<?> incluir(@RequestBody PagamentoDTO pagamentoDTO) {
		
		ValidacaoEntity validacaoEntity = null;
		
		try {
			validacaoEntity = pagamentosService.incluir(pagamentoDTO);
		} catch(PagamentoException ex) {
			if(ex.getValidacao().isVazio()) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}
		
		return ResponseEntity.ok(validacaoEntity);
	}

	@PostMapping(value = "/buscarPorStatus", produces = "application/json")
	public ResponseEntity<?> buscarPorStatus(@RequestBody (required = false) PagamentoDTO pagamentoDTO) {
		
		List<PagamentoDTO> pagamentosDTO = null;
		
		try {
			pagamentosDTO = pagamentosService.buscarPorStatus(pagamentoDTO);
		} catch(PagamentoException ex) {
			if(ex.getValidacao().isVazio()) {
				return new ResponseEntity<>(pagamentosDTO, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}

		return new ResponseEntity<>(pagamentosDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deletar/{id}", produces = "application/json")
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		
		ValidacaoEntity validacaoEntity = null;
		
		try {
			validacaoEntity = pagamentosService.deletar(id);
		} catch(PagamentoException ex) {
			if(ex.getValidacao().isVazio()) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}
		
		return ResponseEntity.ok(validacaoEntity);
	}
	
	@PostMapping(value = "/atualizar", produces = "application/json")
	public ResponseEntity<?> atualizar(@RequestBody PagamentoDTO pagamentoDTO) {
		
		ValidacaoEntity validacaoEntity = null;
		
		try {
			validacaoEntity = pagamentosService.atualizar(pagamentoDTO);
		} catch(PagamentoException ex) {
			if(ex.getValidacao().isVazio()) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}
		
		return ResponseEntity.ok(validacaoEntity);
	}
	

	
	@PostMapping(value = "/atualizarValor", produces = "application/json")
	public ResponseEntity<?> atualizarValor(@RequestBody PagamentoDTO pagamentoDTO) {
		
		ValidacaoEntity validacaoEntity = null;
		
		try {
			validacaoEntity = pagamentosService.atualizarValor(pagamentoDTO);
		} catch(PagamentoException ex) {
			if(ex.getValidacao().isVazio()) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}
		
		return ResponseEntity.ok(validacaoEntity);
	}
}
