package com.api.student.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(BaseStudentException.class)
	public ResponseEntity<RestApiResponseObject> handle(BaseStudentException e) {
		return new ResponseEntity<RestApiResponseObject>(new RestApiResponseObject(e), e.getStatus());
	}

}
