package com.aluno.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluno.spring.model.AlunoModel;
import com.aluno.spring.repository.AlunoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public List<AlunoModel> recuperarTodosalunos() {
		return alunoRepository.findAll();
	}

	public Optional<AlunoModel> recuperarAlunoPorId(Long idAluno) {
		return alunoRepository.findById(idAluno);
	}

	public AlunoModel criarNovoAluno(String nome, Integer idade) {

		AlunoModel alunoModel = new AlunoModel();
		alunoModel.setNome(nome);
		alunoModel.setIdade(idade);

		return alunoRepository.save(alunoModel);
	}

	public AlunoModel atualizarAluno(AlunoModel alunoModel) {

		return alunoRepository.save(alunoModel);
	}

	public void RemoverAluno(Long idAluno) {

		alunoRepository.deleteById(idAluno);
	}

}
