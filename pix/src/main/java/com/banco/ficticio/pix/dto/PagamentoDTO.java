package com.banco.ficticio.pix.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.banco.ficticio.pix.enums.RecorrenciaPagamentoEnum;
import com.banco.ficticio.pix.enums.StatusPagamentoEnum;
import com.banco.ficticio.pix.enums.TipoChaveEnum;

public class PagamentoDTO {

	private Long id;
	private StatusPagamentoEnum status;
	private LocalDateTime dataInclusao;
	private LocalDateTime dataPagamento;
	private BigDecimal valor;
	private String descricao;
	private RecorrenciaPagamentoEnum recorrencia;
	private String destinoChavePix;
	private TipoChaveEnum destinoTipoChave;

	public PagamentoDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPagamentoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusPagamentoEnum status) {
		this.status = status;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public RecorrenciaPagamentoEnum getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(RecorrenciaPagamentoEnum recorrencia) {
		this.recorrencia = recorrencia;
	}

	public String getDestinoChavePix() {
		return destinoChavePix;
	}

	public void setDestinoChavePix(String destinoChavePix) {
		this.destinoChavePix = destinoChavePix;
	}

	public TipoChaveEnum getDestinoTipoChave() {
		return destinoTipoChave;
	}

	public void setDestinoTipoChave(TipoChaveEnum destinoTipoChave) {
		this.destinoTipoChave = destinoTipoChave;
	}

	@Override
	public String toString() {
		return "PagamentoDTO [id=" + id + ", status=" + status + ", dataInclusao=" + dataInclusao + ", dataPagamento="
				+ dataPagamento + ", valor=" + valor + ", descricao=" + descricao + ", recorrencia=" + recorrencia
				+ ", destinoChavePix=" + destinoChavePix + ", destinoTipoChave=" + destinoTipoChave + "]";
	}

}