package com.api.student.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import lombok.Getter;

@Getter
class RestApiExceptionResponse {

	private LocalDateTime timestamp;

	private int statusCode;

	private String message;

	public RestApiExceptionResponse() {
		timestamp = LocalDateTime.now();
	}

	public RestApiExceptionResponse(BaseStudentException e) {
		this();
		statusCode = e.getStatus().value();
		message = e.getMessage();
	}

	public RestApiExceptionResponse(HttpRequestMethodNotSupportedException e) {
		this();
		statusCode = HttpStatus.BAD_REQUEST.value();
		message = e.getMessage();
	}

}
