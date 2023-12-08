package com.example.ing.service.exception;

import java.io.Serial;

public class ProductAlreadyPresentException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 2109719751653377740L;

	public ProductAlreadyPresentException(String message) {
		super(message);
	}
}
