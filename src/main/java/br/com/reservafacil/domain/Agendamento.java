package br.com.reservafacil.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 264094903284079307L;
	
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty(message = "Informar contaOrigem")
	@Size(min = 6, max = 6, message = "Conta destino deve ter {1} caracteres")
	private String contaOrigem;
	@NotEmpty(message = "Informar contaDestino")
	@Size(min = 6, max = 6, message = "Conta destino deve ter {1} caracteres")
	private String contaDestino;
	@NotNull(message = "Informar valor")
	private BigDecimal valor;
	private BigDecimal taxa;
	@NotNull(message = "Informar dataTransferencia")
	private LocalDate dataTransferencia;
	@NotNull
	private LocalDate dataAgendamento = LocalDate.now();
	@NotNull(message = "Informar tipoTransacao")
	private TipoTransacao tipoTransacao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
}
