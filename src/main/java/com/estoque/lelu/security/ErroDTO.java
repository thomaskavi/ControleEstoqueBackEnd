package com.estoque.lelu.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ErroDTO {
	private int status;
	private String message;

	public ErroDTO(int status, String message) {
		this.status = status;
		this.message = message;
	}

}
