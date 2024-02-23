package com.grofers.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GrofersException.class)
	public ResponseEntity<ErrorDetails> handleGrofersException(GrofersException e, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(UsernameNotFoundException e, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// A generic handler for all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception e, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails("An unexpected error occurred", req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserRegistrationException.class)
	public ResponseEntity<ErrorDetails> handleUserRegistrationException(UserRegistrationException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorDetails> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(OrderProcessingException.class)
	public ResponseEntity<ErrorDetails> handleOrderProcessingException(OrderProcessingException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
