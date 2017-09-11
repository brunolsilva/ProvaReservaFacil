package br.com.reservafacil.taxa;

import java.math.BigDecimal;

import br.com.reservafacil.domain.Agendamento;

public interface Taxa {
	
	public BigDecimal calcula(Agendamento agendamento);
}
