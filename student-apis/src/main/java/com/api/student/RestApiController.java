package com.api.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	@GetMapping("/")
	public ResponseObject welcome() {
		return new ResponseObject("Welcome to Student API");
	}

}
