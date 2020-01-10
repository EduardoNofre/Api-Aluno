package com.aluno.spring.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ResourceNotFoundPersonalizada {

	private String titulo;
	private int status;
	private String detalheErro;
	private long timestamp;
	private String classeErro;
	
	private ResourceNotFoundPersonalizada() {
		
	}

}
