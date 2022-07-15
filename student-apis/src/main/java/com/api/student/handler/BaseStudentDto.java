package com.api.student.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseStudentDto {

	private String firstName;

	private String lastName;

	private int age;

}
