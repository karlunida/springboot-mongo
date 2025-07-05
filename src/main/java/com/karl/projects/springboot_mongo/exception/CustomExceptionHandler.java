package com.karl.projects.springboot_mongo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(AuthorNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleAuthorNotFoundException(AuthorNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(AuthorAlreadyExists.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String handleAuthorNotFoundException(AuthorAlreadyExists ex) {
		return ex.getMessage();
	}

}
