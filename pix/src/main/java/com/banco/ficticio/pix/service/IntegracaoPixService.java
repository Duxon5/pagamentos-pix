package com.banco.ficticio.pix.service;

import com.banco.ficticio.pix.dto.IntegracaoPixDTO;
import com.banco.ficticio.pix.enums.TipoAcaoEnum;
import com.banco.ficticio.pix.model.PagamentoModel;

public interface IntegracaoPixService {

	public void informarDDL(PagamentoModel pagamentoModel, TipoAcaoEnum tipoAcao);
	public void incluirFalhaNaIntegracao(IntegracaoPixDTO integracaoPixDTO);
	
}
