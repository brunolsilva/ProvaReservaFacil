package br.com.reservafacil;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import br.com.reservafacil.domain.Agendamento;
import br.com.reservafacil.domain.TipoTransacao;
import br.com.reservafacil.exception.TaxaNaoAplicavelException;

public class TaxaTests {
	
	@Test
	public void testTipoA() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.A);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("33"));
	}
	
	@Test
	public void testTipoANaoAplicavel() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(2));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.A);
		agendamento.setValor(new BigDecimal(1000));
		
		try {
			agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(TaxaNaoAplicavelException.class);
		}
	}
	
	@Test
	public void testTipoB() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(3));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.B);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("12"));
	}
	
	@Test
	public void testTipoBNaoAplicavel() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(25));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.B);
		agendamento.setValor(new BigDecimal(1000));
		try {
			agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(TaxaNaoAplicavelException.class);
		}
	}
	
	@Test
	public void testTipoC10Dias() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(11));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.C);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("82"));
	}
	
	@Test
	public void testTipoC20Dias() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(21));
		agendamento.setId(1000);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTipoTransacao(TipoTransacao.C);
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("69"));
	}
	
	@Test
	public void testTipoC30Dias() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(31));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.C);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("47"));
	}
	
	@Test
	public void testTipoC40Dias() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(41));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.C);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("17"));
	}
	
	@Test
	public void testTipoCNaoAplicavel() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.C);
		agendamento.setValor(new BigDecimal(1000));
		try {
			agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(TaxaNaoAplicavelException.class);
		}
	}
	
	@Test
	public void testTipoDRegraA() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.D);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("33"));
	}
	
	@Test
	public void testTipoDRegraB() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(8));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.D);
		agendamento.setValor(new BigDecimal(2000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("12"));
	}
	
	@Test
	public void testTipoDRegraC() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(21));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.D);
		agendamento.setValor(new BigDecimal(3000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		assertThat(agendamento.getTaxa()).isEqualByComparingTo(new BigDecimal("207"));
	}
}
