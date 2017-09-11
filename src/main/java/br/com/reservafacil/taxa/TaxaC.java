package br.com.reservafacil.taxa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.reservafacil.domain.Agendamento;
import br.com.reservafacil.exception.TaxaNaoAplicavelException;

public class TaxaC implements Taxa {

	@Override
	public BigDecimal calcula(Agendamento agendamento) {
		
		long dias = quantidadeDias(
				agendamento.getDataAgendamento(),
				agendamento.getDataTransferencia());
		if (dias > 40) {
			return calculaValorTaxa(agendamento.getValor(), new BigDecimal("1.7"));
		} else if (dias > 30){
			return calculaValorTaxa(agendamento.getValor(), new BigDecimal("4.7"));
		} else if (dias > 20) {
			return calculaValorTaxa(agendamento.getValor(), new BigDecimal("6.9"));
		} else if (dias > 10) {
			return calculaValorTaxa(agendamento.getValor(), new BigDecimal("8.2"));
		}
		
		throw new TaxaNaoAplicavelException();
	}
	
	private static long quantidadeDias(LocalDate data1, LocalDate data2) {
		if(data1 == null || data2 == null) {
			throw new IllegalArgumentException();
		}
		return ChronoUnit.DAYS.between(data1, data2);
	}
	
	private static BigDecimal calculaValorTaxa(BigDecimal valor, BigDecimal percentual) {
		return valor.multiply(percentual.divide(new BigDecimal(100)));
	}

}
