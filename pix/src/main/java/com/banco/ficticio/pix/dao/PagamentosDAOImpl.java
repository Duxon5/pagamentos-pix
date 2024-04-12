package com.banco.ficticio.pix.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banco.ficticio.pix.exception.PagamentoException;
import com.banco.ficticio.pix.model.PagamentoModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class PagamentosDAOImpl implements PagamentosDAO {

	@Autowired
	private PagamentosRepository pagamentosRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void incluir(PagamentoModel pagamentoModel) throws PagamentoException {
		pagamentosRepository.save(pagamentoModel);
	}

	@Override
	public void atualizar(PagamentoModel pagamentoModel) throws PagamentoException {
		pagamentosRepository.save(pagamentoModel);

	}

	@Override
	public void atualizarValor(PagamentoModel pagamentoModel) throws PagamentoException {
		pagamentosRepository.save(pagamentoModel);
	}

	@Override
	public void deletar(PagamentoModel pagamentoModel) throws PagamentoException {
		pagamentosRepository.deleteById(pagamentoModel.getId());
	}

	@Override
	public List<PagamentoModel> buscarPorStatus(PagamentoModel pagamentoModel) throws PagamentoException {
		
		List<PagamentoModel> pagamentosModel = null;
		
		if(pagamentoModel.getStatus() == null) {
			pagamentosModel = pagamentosRepository.findAll();
		} else {
			pagamentosModel = pagamentosRepository.findByStatus(pagamentoModel.getStatus());
		}
		
		return pagamentosModel;
	}

	/**
	 * Filtra por: dataPagamento (apenas data), valor e destinoChavePix
	 */
	@Override
	public List<PagamentoModel> buscarPorCondicoesIguais(PagamentoModel pagamentoModel) throws PagamentoException { 

	    // Cria a query HQL
	    String hql =  "SELECT p FROM PagamentoModel p WHERE "
	    				+ "CAST(p.dataPagamento AS java.time.LocalDate) = :dataPagamento AND "
	    				+ "p.valor = :valor AND "
	    				+ "p.destinoChavePix = :destinoChavePix";

	    Query query = entityManager.createQuery(hql);
	    
	    query.setParameter("dataPagamento", pagamentoModel.getDataPagamento().toLocalDate());
	    query.setParameter("valor", pagamentoModel.getValor());
	    query.setParameter("destinoChavePix", pagamentoModel.getDestinoChavePix());

	    @SuppressWarnings("unchecked")
		List<PagamentoModel> pagamentosModel = query.getResultList();

	    return pagamentosModel;
	}

	@Override
	public Optional<PagamentoModel> buscarPorId(Long id) throws PagamentoException {
		return pagamentosRepository.findById(id);
	}

}
