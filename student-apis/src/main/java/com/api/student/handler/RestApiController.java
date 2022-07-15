package com.api.student.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.student.ResponseObject;

@RestController("StudentRestApiController")
@RequestMapping("/students")
public class RestApiController {

	private StudentService service;

	public RestApiController(StudentService service) {
		this.service = service;
	}

	@GetMapping("")
	public List<StudentDto> getListOfStudents() {
		return service.getAllStudents();
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentDto addStudent(@RequestBody BaseStudentDto newStudent) {
		return service.addNewStudent(newStudent);
	}

	@GetMapping("/{studentId}")
	public StudentDto getStudentById(@PathVariable Long studentId) {
		return service.getStudent(studentId);
	}

	@PutMapping("/{studentId}")
	public ResponseObject updateStudentDetails(@PathVariable Long studentId,
			@RequestBody BaseStudentDto updateStudent) {
		service.updateStudent(studentId, updateStudent);
		return new ResponseObject("Student details successfully updated");
	}

	@DeleteMapping("/{studentId}")
	public ResponseObject deleteStudent(@PathVariable Long studentId) {
		service.deleteStudent(studentId);
		return new ResponseObject("Student details successfully deleted");
	}

}
