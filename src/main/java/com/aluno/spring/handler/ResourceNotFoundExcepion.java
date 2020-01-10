package com.aluno.spring.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundExcepion(String message) {
		super(message);
	}

}
