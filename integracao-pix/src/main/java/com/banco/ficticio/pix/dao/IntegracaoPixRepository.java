package com.banco.ficticio.pix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.ficticio.pix.model.IntegracaoPixModel;

@Repository
public interface IntegracaoPixRepository extends JpaRepository<IntegracaoPixModel, Long>  {

}
