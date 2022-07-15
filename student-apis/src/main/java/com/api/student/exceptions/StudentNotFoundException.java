package com.api.student.exceptions;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseStudentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453133101097090528L;

	private long studentId;

	public StudentNotFoundException(long invalidStudentId) {
		studentId = invalidStudentId;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public String getMessage() {
		return "Student does not exist for ID - " + studentId;
	}

}
