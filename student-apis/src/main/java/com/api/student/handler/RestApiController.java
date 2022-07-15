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
	public List<Student> getListOfStudents() {
		return service.getAllStudents();
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student addStudent(@RequestBody Student student) {
		return service.addNewStudent(student);
	}

	@GetMapping("/{studentId}")
	public Student getStudentById(@PathVariable Long studentId) {
		return service.getStudent(studentId);
	}

	@PutMapping("/{studentId}")
	public ResponseObject updateStudentDetails(@PathVariable Long studentId, @RequestBody Student newStudent) {
		service.updateStudent(studentId, newStudent);
		return new ResponseObject("Student details successfully updated");
	}

	@DeleteMapping("/{studentId}")
	public ResponseObject deleteStudent(@PathVariable Long studentId) {
		service.deleteStudent(studentId);
		return new ResponseObject("Student details successfully deleted");
	}

}
