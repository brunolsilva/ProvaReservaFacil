package br.com.reservafacil.taxa;

import java.math.BigDecimal;

import br.com.reservafacil.domain.Agendamento;

public class TaxaD implements Taxa {

	@Override
	public BigDecimal calcula(Agendamento agendamento) {
		if (agendamento.getValor().compareTo(new BigDecimal("2000")) > 0) {
			return new TaxaC().calcula(agendamento);
		} else if (agendamento.getValor().compareTo(new BigDecimal("1000")) > 0) {
			return new TaxaB().calcula(agendamento);
		}
		
		return new TaxaA().calcula(agendamento);
	}
}
