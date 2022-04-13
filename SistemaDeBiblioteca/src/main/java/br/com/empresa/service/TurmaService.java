package br.com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.Turma;
import br.com.empresa.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository repo;
	
	//criar metodo de listar todas as turmas
	public List<Turma> listaTodasTurmas(){
		return repo.findAll();
		
	}
	
	
	//criar metodo para trazer a turma por id
	
	public Turma buscaPorID(Integer id) throws ObjectNotFoundException{
		Optional<Turma> turma = repo.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrada"));
	}
	
	//criar metodo para inserir a turma
	public Turma salvar(Turma turma) {
		return repo.save(turma);
	}
	
	
	//criar metodo para fazer update da turma
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorID(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar(turma);
	}
	
	
	//criar metodo ´para excluir turma

	public void excluir(Integer id) {
		repo.deleteById(id);
	}
}