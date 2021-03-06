package br.com.empresa.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.constantes.Messages;
import br.com.empresa.entity.Aluno;
import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "EndPoint Avaliação")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listarAvaliacao(){
		List<Avaliacao> listaAvaliacao = service.findAll();
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao objAvaliacao){
		objAvaliacao = service.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
		
		
	}
	
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value = "/{id}/{idDisciplina}", method = RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoPorDisciplina(Integer IdAluno, Integer idDisciplina){
		Aluno aluno = new Aluno();
		aluno.setId(IdAluno);
		
		Disciplina disciplina = new Disciplina();

		disciplina.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		Avaliacao availiacao = service.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok().body(availiacao);
		
	}

}
