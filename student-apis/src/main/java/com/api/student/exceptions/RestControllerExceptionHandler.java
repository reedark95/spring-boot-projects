package com.api.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(BaseStudentException.class)
	public ResponseEntity<RestApiExceptionResponse> handle(BaseStudentException e) {
		return new ResponseEntity<RestApiExceptionResponse>(new RestApiExceptionResponse(e), e.getStatus());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<RestApiExceptionResponse> handle(HttpRequestMethodNotSupportedException e) {
		return new ResponseEntity<RestApiExceptionResponse>(new RestApiExceptionResponse(e), HttpStatus.BAD_REQUEST);
	}

}
