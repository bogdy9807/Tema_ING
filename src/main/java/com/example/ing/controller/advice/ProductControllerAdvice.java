package com.example.ing.controller.advice;

import com.example.ing.service.exception.ProductAlreadyPresentException;
import com.example.ing.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

	private static final String EXCEPTION_OCCURRED = "Exception occurred: ";

	@ExceptionHandler(value = {ProductNotFoundException.class})
	protected ResponseEntity<Object> handleInternalException(ProductNotFoundException ex) {
		log.error(EXCEPTION_OCCURRED, ex);
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {ProductAlreadyPresentException.class})
	protected ResponseEntity<Object> handleInternalException(ProductAlreadyPresentException ex) {
		log.error(EXCEPTION_OCCURRED, ex);
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
