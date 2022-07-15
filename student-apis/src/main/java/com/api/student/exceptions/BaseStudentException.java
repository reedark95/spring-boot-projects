package com.api.student.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public abstract class BaseStudentException extends RuntimeException {

	abstract public HttpStatus getStatus();

	abstract public String getMessage();

}
