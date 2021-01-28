package escola.api.alf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import escola.api.alf.model.Aluno;
import escola.api.alf.model.Prova;
import escola.api.alf.repository.AlunoRepository;
import escola.api.alf.repository.ProvaRepository;

@RestController
@RequestMapping(value = "/aluno")
public class IndexController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ProvaRepository provaRepository;

	// 1) Buscar todos que estão registrados no BD
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Aluno>> listarAlunos() {

		List<Aluno> alunos = (List<Aluno>) alunoRepository.findAll();
		return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
	}

	// Listar os alunos aprovados
	@GetMapping(value = "/aprovados", produces = "application/json")
	public ResponseEntity<List<Aluno>> listaDeAprovados() {

		List<Aluno> alunosAprovados = alunoRepository.findAllAprovados();
		return new ResponseEntity<List<Aluno>>(alunosAprovados, HttpStatus.OK);
	}

	// Cadastrar um novo aluno e as alternativas para as 4 avaliações
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {

		Long qtdAlunos = alunoRepository.findTotalRegistrados();

		if (qtdAlunos > 100) {
			return null;
		}

		if (qtdAlunos != 0) {

			List<String> gabaritoProva = provaRepository.findByRespostas(1L);
			List<String> alternativasAluno = new ArrayList<>();

			for (int i = 0; i < aluno.getProvas().size(); i++) {
				alternativasAluno.add(aluno.getProvas().get(i).toString2());
			}

			Prova provasAluno = new Prova();

			List<Double> notasProvas = provasAluno.getNotaProva(alternativasAluno, gabaritoProva);
			aluno.setSomaNotas(notasProvas);
			aluno.setAprovado(aluno.getAprovado());

		} else {
			aluno.setNotaFinal(9.9);
			aluno.setAprovado(true);
		}

		for (int i = 0; i < aluno.getProvas().size(); i++) {
			aluno.getProvas().get(i).setAluno(aluno);
		}

		Aluno alunoCadastrado = alunoRepository.save(aluno);

		return new ResponseEntity<Aluno>(alunoCadastrado, HttpStatus.OK);

	}
}