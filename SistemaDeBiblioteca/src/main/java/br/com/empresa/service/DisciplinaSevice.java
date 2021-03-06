package br.com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.Disciplina;
import br.com.empresa.repository.DiscisplinaRepository;

@Service
public class DisciplinaSevice {
	
	@Autowired
	private DiscisplinaRepository disciplinaRepo;
	
	
	
	public List<Disciplina> listaTodasDisciplinas(){
		
		return disciplinaRepo.findAll();
		}

	
	public Disciplina buscaPorId(Integer id) throws ObjectNotFoundException{
		Optional<Disciplina> disciplina = disciplinaRepo.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException(null, "Disciplina não encontrada"));
	}
	
	public Disciplina salvar(Disciplina disciplina) {
		return disciplinaRepo.save(disciplina);
	}
	
	public void excluir(Integer id) {
		disciplinaRepo.deleteById(id);
	}
	public Disciplina alterar(Disciplina objDisciplina) {
		Disciplina disc = buscaPorId(objDisciplina.getId());
		disc.setNome(objDisciplina.getNome());
		return salvar(disc);
	}
	
	
	
}


