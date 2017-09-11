package br.com.reservafacil.domain;

import java.math.BigDecimal;

import br.com.reservafacil.taxa.TaxaA;
import br.com.reservafacil.taxa.TaxaB;
import br.com.reservafacil.taxa.TaxaC;
import br.com.reservafacil.taxa.TaxaD;

public enum TipoTransacao {
	
	A {
		@Override
		public BigDecimal calculaTaxa(Agendamento agendamento) {
			return new TaxaA().calcula(agendamento);
		}
	},
	B {
		@Override
		public BigDecimal calculaTaxa(Agendamento agendamento) {
			return new TaxaB().calcula(agendamento);
		}
	},
	C {
		@Override
		public BigDecimal calculaTaxa(Agendamento agendamento) {
			return new TaxaC().calcula(agendamento);
		}
	},
	D {
		@Override
		public BigDecimal calculaTaxa(Agendamento agendamento) {
			return new TaxaD().calcula(agendamento);
		}
	};
	
	public abstract BigDecimal calculaTaxa(Agendamento agendamento);
}