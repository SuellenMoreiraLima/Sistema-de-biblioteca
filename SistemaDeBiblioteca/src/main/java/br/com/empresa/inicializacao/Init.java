package br.com.empresa.inicializacao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.empresa.entity.Aluno;
import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.entity.Turma;
import br.com.empresa.repository.AlunoRepository;
import br.com.empresa.service.AvaliacaoService;
import br.com.empresa.service.DisciplinaSevice;
import br.com.empresa.service.TurmaService;


@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Autowired
	TurmaService turmaService;
	
	@Autowired
	DisciplinaSevice disciplinaService;
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	public void onApplicationEvent(ContextRefreshedEvent event){
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Arthur");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("João");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Maria");
		
		alunoRepo.saveAll(Arrays.asList(aluno1,aluno2, aluno3, aluno4));
		
             Turma ads = new Turma();
             ads.setNome("ADS");
             
             turmaService.salvar(ads);
             
             Turma rede = new Turma();
             rede.setNome("Rede");
             
             turmaService.salvar(rede);
             
             

             
             Turma turma1 = turmaService.buscaPorID(2);
             
             System.out.println(turma1.getNome());
             
             //turmaService.excluir(1);
             


             
             Turma turmaAlterar = new Turma();
             turmaAlterar.setId(1);
             turmaAlterar.setNome("Rede");
             
             turmaService.alterar(turmaAlterar);
             
             List<Turma> listaTurmas = turmaService.listaTodasTurmas();
             
             for(Turma turma : listaTurmas) {
            	 System.out.println("Nome da turma: " + turma.getNome());
             }
		
             
             Disciplina java = new Disciplina();
             java.setNome("Java");
             disciplinaService.salvar(java);
             
             Disciplina java2 = new Disciplina();
             java2.setNome("Java2");
             disciplinaService.salvar(java2);
             
             Disciplina arquitetura = new Disciplina();
             arquitetura.setNome("Arquitetura");
             disciplinaService.salvar(arquitetura);
             
             
             aluno1.setDisciplinas(Arrays.asList(java, arquitetura, java2));
             aluno2.setDisciplinas(Arrays.asList(java, java2));
             aluno3.setDisciplinas(Arrays.asList(arquitetura, java2));
             aluno4.setDisciplinas(Arrays.asList(java, arquitetura));
             
             aluno1.setTurma(ads);
             aluno2.setTurma(ads);
             aluno3.setTurma(rede);
             aluno4.setTurma(ads);
             
             alunoRepo.save(aluno1);
             alunoRepo.save(aluno2);
             alunoRepo.save(aluno3);
             alunoRepo.save(aluno4);
             
             Avaliacao avaliacaoAluno1 = new Avaliacao();
             
             AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
             alunoDisciplina.setAluno(aluno1);
             alunoDisciplina.setDisciplina(arquitetura);
             
             avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina);
             avaliacaoAluno1.setConceito("A");
             avaliacaoService.save(avaliacaoAluno1);
             
             AlunoDisciplina joaoJava = new AlunoDisciplina();
             joaoJava.setAluno(aluno2);
             joaoJava.setDisciplina(arquitetura);
             
             Avaliacao avaliacaoJoaoJava = new Avaliacao();
             avaliacaoJoaoJava.setAlunoDisciplina(joaoJava);
             avaliacaoJoaoJava.setConceito("B");
             avaliacaoService.save(avaliacaoJoaoJava);
             
             
             
             Avaliacao aval = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
             System.out.println("Aluno : " + aval.getAlunoDisciplina().getAluno().getNome());
             System.out.println("Disciplina: " + aval.getAlunoDisciplina().getDisciplina().getNome());
             System.out.println("Avaliação: " + aval.getConceito());
	}

}
