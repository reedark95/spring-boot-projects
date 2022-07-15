package com.api.student.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StudentDto extends BaseStudentDto {

	private long id;

	@Builder(builderMethodName = "studentDtoBuilder")
	StudentDto(String firstName, String lastName, int age, long id) {
		super(firstName, lastName, age);
		this.id = id;
	}

}
