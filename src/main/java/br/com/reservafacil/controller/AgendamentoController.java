package br.com.reservafacil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reservafacil.domain.Agendamento;
import br.com.reservafacil.repository.AgendamentoRepository;

/**
 * Controller REST para gerenciamento de agendamentos
 *
 */
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;

	/**
	 * GET  /agendamento : exibe todos os agendamentos.
	 *
	 * @return status 200 com a lista de Agendamento no corpo
	 */
	@GetMapping
	public ResponseEntity<List<Agendamento>> listar() {
		List<Agendamento> agendamentos = agendamentoRepository.findAll();
		return ResponseEntity.ok(new ArrayList<>(agendamentos));
	}
	
	/**
	 * POST  /agendamento : Cria um novo agendamento.
	 *
	 * @param agendamento
	 * @return status 201 com o agendamento no corpo ou 400 se encontrar algum erro na requisição
	 */
	@PostMapping
	public ResponseEntity<Agendamento> criar(@Valid @RequestBody Agendamento agendamento) {
		agendamento.setTaxa(agendamento.getTipoTransacao().calculaTaxa(agendamento));
		Agendamento agendamentoCriado = agendamentoRepository.save(agendamento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoCriado);
	}
}
