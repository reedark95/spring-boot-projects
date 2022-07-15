package com.api.student.exceptions;

import org.springframework.http.HttpStatus;

public class StudentAlreadyExistException extends BaseStudentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6882311711105674346L;

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "Duplicate student details not allowed";
	}

}
