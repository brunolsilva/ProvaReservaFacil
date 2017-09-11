package br.com.reservafacil.taxa;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.reservafacil.domain.Agendamento;
import br.com.reservafacil.exception.TaxaNaoAplicavelException;

public class TaxaA implements Taxa {

	@Override
	public BigDecimal calcula(Agendamento agendamento) {
		if(agendamento.getDataTransferencia().isEqual(LocalDate.now())) {
			BigDecimal taxa = new BigDecimal(3);
			return agendamento.getValor()
					.multiply(new BigDecimal("0.03"))
					.add(taxa);
		}
		
		throw new TaxaNaoAplicavelException();
	}
}
