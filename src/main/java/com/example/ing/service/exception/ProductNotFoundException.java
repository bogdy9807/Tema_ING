package com.example.ing.service.exception;

import java.io.Serial;

public class ProductNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1551817511353821146L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
