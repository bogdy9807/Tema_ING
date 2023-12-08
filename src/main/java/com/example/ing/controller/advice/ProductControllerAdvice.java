package com.example.ing.controller.advice;

import com.example.ing.service.exception.ProductAlreadyPresentException;
import com.example.ing.service.exception.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ProductNotFoundException.class})
	protected ResponseEntity<Object> handleInternalException(ProductNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {ProductAlreadyPresentException.class})
	protected ResponseEntity<Object> handleInternalException(ProductAlreadyPresentException ex) {
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}
