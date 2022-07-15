package com.api.student.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
class RestApiExceptionResponse {

	private LocalDateTime timestamp;

	private int statusCode;

	private String message;

	public RestApiExceptionResponse(BaseStudentException e) {
		timestamp = LocalDateTime.now();
		statusCode = e.getStatus().value();
		message = e.getMessage();
	}

}
