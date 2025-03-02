package com.stacksimpllify.restservices.springboot_buildingblock.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails userNameNotFound(UserNameNotFoundException ex) {
		return new CustomErrorDetails(LocalDateTime.now(),"From @RestControllerAdvice Not Found",ex.getMessage());
	}

}
