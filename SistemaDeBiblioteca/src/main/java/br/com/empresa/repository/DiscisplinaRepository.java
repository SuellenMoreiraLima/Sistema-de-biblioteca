package br.com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.entity.Disciplina;

@Repository
public interface DiscisplinaRepository extends JpaRepository<Disciplina, Integer>{

}
