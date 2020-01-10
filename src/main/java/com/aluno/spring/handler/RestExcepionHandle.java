package com.aluno.spring.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExcepionHandle {

	@ExceptionHandler(ResourceNotFoundExcepion.class)
	public ResponseEntity<?> handleNotFoundExcepion(ResourceNotFoundExcepion rnfe){
		
		ResourceNotFoundPersonalizada resourceNotFoundPersonalizada = ResourceNotFoundPersonalizada
		.builder().timestamp(new Date().getTime())
		.status(HttpStatus.NOT_FOUND.value())
		.titulo("Recurso não encontrado")
		.detalheErro(rnfe.getMessage())
		.classeErro(rnfe.getClass().getName())
		.build();
		
		return new ResponseEntity<>(resourceNotFoundPersonalizada, HttpStatus.NOT_FOUND);
	}
}
