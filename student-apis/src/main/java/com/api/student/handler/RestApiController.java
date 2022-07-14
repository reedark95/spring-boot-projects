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
import org.springframework.web.server.ResponseStatusException;

import com.api.student.ResponseObject;

@RestController("StudentRestApiController")
@RequestMapping("/students")
public class RestApiController {

	private StudentRepository repository;

	public RestApiController(StudentRepository repository) {
		this.repository = repository;
	}

	private Student getStudent(Long studentId) {
		return repository.findById(studentId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found for ID - " + studentId));
	}

	@GetMapping("")
	public List<Student> getListOfStudents() {
		return repository.findAll();
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student addStudent(@RequestBody Student student) {
		return repository.save(student);
	}

	@GetMapping("/{studentId}")
	public Student getStudentById(@PathVariable Long studentId) {
		return repository.findById(studentId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found for ID - " + studentId));
	}

	@PutMapping("/{studentId}")
	public ResponseObject updateStudentDetails(@PathVariable Long studentId, @RequestBody Student newStudent) {
		Student oldStudent = getStudent(studentId);

		Student updatedStudent = oldStudent.toBuilder().firstName(newStudent.getFirstName())
				.lastName(newStudent.getLastName()).age(newStudent.getAge()).build();

		repository.save(updatedStudent);

		return new ResponseObject("Student details successfully updated");
	}

	@DeleteMapping("/{studentId}")
	public ResponseObject deleteStudent(@PathVariable Long studentId) {
		Student student = getStudent(studentId);
		repository.deleteById(student.getId());

		return new ResponseObject("Student details successfully deleted");
	}

}
