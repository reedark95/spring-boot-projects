package com.api.student.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(BaseStudentException.class)
	public ResponseEntity<RestApiExceptionResponse> handle(BaseStudentException e) {
		return new ResponseEntity<RestApiExceptionResponse>(new RestApiExceptionResponse(e), e.getStatus());
	}

}
