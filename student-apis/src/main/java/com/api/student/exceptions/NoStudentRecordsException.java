package com.api.student.exceptions;

import org.springframework.http.HttpStatus;

public class NoStudentRecordsException extends BaseStudentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1927639201137475046L;

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "No collection of student records stored";
	}

}
