package com.banco.ficticio.pix.model;

import java.time.LocalDateTime;

import com.banco.ficticio.pix.enums.TipoAcaoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "integracao_pix")
public class IntegracaoPixModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idPix;
	private TipoAcaoEnum tipoAcao;
	private LocalDateTime dataAcao;

	public IntegracaoPixModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPix() {
		return idPix;
	}

	public void setIdPix(Long idPix) {
		this.idPix = idPix;
	}

	public TipoAcaoEnum getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(TipoAcaoEnum tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public LocalDateTime getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(LocalDateTime dataAcao) {
		this.dataAcao = dataAcao;
	}

}