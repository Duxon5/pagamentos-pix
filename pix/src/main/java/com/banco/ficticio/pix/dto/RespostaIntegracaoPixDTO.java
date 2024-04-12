package com.banco.ficticio.pix.dto;

import com.banco.ficticio.pix.enums.WSTipoRespostaEnum;

public class RespostaIntegracaoPixDTO {

	private WSTipoRespostaEnum tipoResposta; 
	private IntegracaoPixDTO integracaoPixDTO;

	public RespostaIntegracaoPixDTO() {
		super();
	}

	public WSTipoRespostaEnum getTipoResposta() {
		return tipoResposta;
	}

	public void setTipoResposta(WSTipoRespostaEnum tipoResposta) {
		this.tipoResposta = tipoResposta;
	}

	public IntegracaoPixDTO getIntegracaoPixDTO() {
		return integracaoPixDTO;
	}

	public void setIntegracaoPixDTO(IntegracaoPixDTO integracaoPixDTO) {
		this.integracaoPixDTO = integracaoPixDTO;
	}

}