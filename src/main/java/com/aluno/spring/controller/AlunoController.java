/**
 * 
 */
package com.aluno.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluno.spring.handler.ResourceNotFoundExcepion;
import com.aluno.spring.model.AlunoModel;
import com.aluno.spring.service.AlunoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Eduardo Nofre
 *
 */

@Api(value = "/v1/aluno", description = "Responsável por Aluno")
@RestController
@Validated
@RequestMapping("/v1/aluno")
@CrossOrigin(origins="*")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@ApiOperation(value = "Busca todos os alunos", response = ResponseEntity.class)
	@ApiResponses(value = {@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Aluno."),
						   @ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.")})
	
	@GetMapping(path = "/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity buscaTodosalunos() {

		List<AlunoModel> alunosModelResponse = alunoService.recuperarTodosalunos();

		return new ResponseEntity<>(alunosModelResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Recuperar um aluno pelo id", response = ResponseEntity.class)
	@ApiResponses(value = {@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Aluno."),
					       @ApiResponse(code = 404, message = "HttpStatus 404 = Id do aluno não foi encontrado."),
					       @ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.")})
	
	@GetMapping(path = "/consultar/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity buscaAlunoPorid(@ApiParam(name = "Id do aluno", required = true) @NotNull @PathVariable("id") Long idAluno) {

		verificaExisteAluno(idAluno);
		
		Optional<AlunoModel> alunoModelResponse = alunoService.recuperarAlunoPorId(idAluno);

		return new ResponseEntity<>(alunoModelResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Criar um novo aluno", response = ResponseEntity.class)
	@ApiResponses(value = {@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Aluno."),
						   @ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.")})
	
	@PostMapping(path = "/insereAluno/nome/{nome}/idade/{idade}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity insereAluno(@ApiParam(name = "Nome do aluno", required = false) @NotNull @PathVariable("nome") String nome,
									  @ApiParam(name = "Idade do aluno", required = false) @NotNull @PathVariable("idade") Integer idade) {

		AlunoModel alunoModelResponse =  alunoService.criarNovoAluno(nome,idade);

		return new ResponseEntity<>(alunoModelResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Atualizar um aluno", response = ResponseEntity.class)
	@ApiResponses(value = {@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Aluno."),
						   @ApiResponse(code = 404, message = "HttpStatus 404 = Id do aluno não foi encontrado."),
						   @ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.")})
	
	@PutMapping(path = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity atualizarDadosAluno(@ApiParam(name = "Dados do aluno a ser atualizada.", required = true) @RequestBody @Valid AlunoModel alunoModel) {

		verificaExisteAluno(alunoModel.getId());
		
		AlunoModel alunoModelResponse = alunoService.atualizarAluno(alunoModel);

		return new ResponseEntity<>(alunoModelResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Remover um aluno", response = ResponseEntity.class)
	@ApiResponses(value = {@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Aluno."),
						   @ApiResponse(code = 404, message = "HttpStatus 404 = Id do aluno não foi encontrado."),
	                       @ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.")})
	
	@DeleteMapping(path = "/remover/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity excluirAluno(@ApiParam(name = "Id do aluno", required = true) @NotNull @PathVariable("id") Long idAluno) {

		verificaExisteAluno(idAluno);
		
		alunoService.RemoverAluno(idAluno);

		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
	
	private void verificaExisteAluno(Long idAluno) {

		if (!alunoService.recuperarAlunoPorId(idAluno).isPresent()) {

			throw new ResourceNotFoundExcepion(" Aluno não existe para o ID: " + idAluno);
		}
	}
}
