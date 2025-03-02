package com.stacksimpllify.restservices.springboot_buildingblock.exceptions;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
				"From MethodArgumentNotValid Exception in GEH", 
				ex.getMessage());

		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
				"From MethodNotAllowed Exception in GEH", 
				ex.getMessage());
		return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(
			UserNameNotFoundException ex, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
				"From UserNameNotFound Exception in GEH - "+ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
	}
	
	@Override
	public final ResponseEntity<Object> handleHandlerMethodValidationException(
			HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
				"Invalid Input Argument - "+ex.getMessage(), 
				request.getDescription(false));		
		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
}	
