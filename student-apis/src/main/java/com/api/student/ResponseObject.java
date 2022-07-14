package com.api.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ResponseObject {

	@NonNull
	private String message;

}
