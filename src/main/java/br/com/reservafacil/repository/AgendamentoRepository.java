package br.com.reservafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reservafacil.domain.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	
}
