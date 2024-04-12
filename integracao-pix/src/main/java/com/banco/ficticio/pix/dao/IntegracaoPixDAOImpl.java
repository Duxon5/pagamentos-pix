package com.banco.ficticio.pix.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banco.ficticio.pix.model.IntegracaoPixModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class IntegracaoPixDAOImpl implements IntegracaoPixDAO {

	@Autowired
	private IntegracaoPixRepository integracaoPixRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void incluir(IntegracaoPixModel integracaoPixModel) throws Exception {
		integracaoPixRepository.save(integracaoPixModel);
	}

	@Override
	public List<IntegracaoPixModel> buscarTodos() throws Exception {
		return integracaoPixRepository.findAll();
	}

}
