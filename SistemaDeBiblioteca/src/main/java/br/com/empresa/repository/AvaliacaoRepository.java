package br.com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina> {
	
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);
}
