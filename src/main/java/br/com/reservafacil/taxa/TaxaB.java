package br.com.reservafacil.taxa;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import br.com.reservafacil.domain.Agendamento;
import br.com.reservafacil.exception.TaxaNaoAplicavelException;

public class TaxaB implements Taxa {

	@Override
	public BigDecimal calcula(Agendamento agendamento) {
		if (ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia()) <= 10) {
			return new BigDecimal(12);
		}
		throw new TaxaNaoAplicavelException();
	}
}
