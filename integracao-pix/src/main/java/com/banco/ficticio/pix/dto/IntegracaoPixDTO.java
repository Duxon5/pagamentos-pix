package com.banco.ficticio.pix.dto;

import java.time.LocalDateTime;

import com.banco.ficticio.pix.enums.TipoAcaoEnum;

public class IntegracaoPixDTO {

	private Long id;
	private Long idPix;
	private TipoAcaoEnum tipoAcao;
	private LocalDateTime dataAcao;

	public IntegracaoPixDTO() {
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