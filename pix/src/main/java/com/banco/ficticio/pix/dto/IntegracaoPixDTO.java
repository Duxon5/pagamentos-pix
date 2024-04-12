package com.banco.ficticio.pix.dto;

import java.time.LocalDateTime;

import com.banco.ficticio.pix.enums.TipoAcaoEnum;
import com.banco.ficticio.pix.model.PagamentoModel;

public class IntegracaoPixDTO {

	private Long id;
	private Long idPix;
	private TipoAcaoEnum tipoAcao;
	private LocalDateTime dataAcao;

	public IntegracaoPixDTO() {
		super();
	}

	/**
	 * Por este construtor, manda sem ID, pois não foi inserido no BD
	 */
	public IntegracaoPixDTO(PagamentoModel pagamentoModel, TipoAcaoEnum tipoAcao) {
		this.idPix = pagamentoModel.getId();
		this.tipoAcao = tipoAcao;
		this.dataAcao = LocalDateTime.now();
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