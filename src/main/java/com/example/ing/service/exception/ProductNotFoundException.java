package com.example.ing.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1551817511353821146L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
