package br.com.reservafacil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.reservafacil.controller.AgendamentoController;
import br.com.reservafacil.domain.Agendamento;
import br.com.reservafacil.domain.TipoTransacao;
import br.com.reservafacil.exception.TaxaNaoAplicavelException;
import br.com.reservafacil.repository.AgendamentoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AgendamentoController.class)
@AutoConfigureTestDatabase
public class AgendamentoControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AgendamentoRepository agendamentoRepository;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);
		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}
	
	@Before
	public void setup() {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.A);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		given(this.agendamentoRepository.findAll())
			.willReturn(Arrays.asList(agendamento));
	}
	
	@Test
	public void testConsultaAgendamentos() throws Exception {
		this.mvc.perform(get("/agendamento").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{'id': 1000, 'taxa': 33}]"));
	}
	
	@Test
	public void testCriaAgendamentoSucesso() throws Exception {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.A);
		agendamento.setValor(new BigDecimal(1000));
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		
		this.mvc.perform(post("/agendamento").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json(agendamento)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testCriaAgendamentoTaxaNaoAplicavel() throws Exception {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		agendamento.setDataTransferencia(LocalDate.now().plusDays(30));
		agendamento.setId(1000);
		agendamento.setTipoTransacao(TipoTransacao.B);
		agendamento.setValor(new BigDecimal(1000));
		try {
			agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(TaxaNaoAplicavelException.class);
		}
		
		this.mvc.perform(post("/agendamento").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json(agendamento)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCriaAgendamentoCamposInvalidos() throws Exception {
		Agendamento agendamento = new Agendamento();
		agendamento.setContaDestino("123456");
		agendamento.setContaOrigem("ABCDEF");
		
		this.mvc.perform(post("/agendamento").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json(agendamento)))
				.andExpect(status().isBadRequest());
	}
	
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
