package com.banco.ficticio.pix.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.ficticio.pix.enums.StatusPagamentoEnum;
import com.banco.ficticio.pix.model.PagamentoModel;

@Repository
public interface PagamentosRepository extends JpaRepository<PagamentoModel, Long>  {

	public List<PagamentoModel> findByStatus(StatusPagamentoEnum status);
	public Optional<PagamentoModel> findById(Long id);

}
