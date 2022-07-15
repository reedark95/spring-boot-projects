package com.api.student.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.student.exceptions.NoStudentRecordsException;
import com.api.student.exceptions.StudentAlreadyExistException;
import com.api.student.exceptions.StudentNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository repository;

	public StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> list = repository.findAll();

		if (list.size() == 0)
			throw new NoStudentRecordsException();

		return list;
	}

	@Override
	public Student addNewStudent(Student newStudent) {
		if (repository.findByFirstNameAndLastNameAndAge(newStudent.getFirstName(), newStudent.getLastName(),
				newStudent.getAge()).isPresent())
			throw new StudentAlreadyExistException();

		return repository.save(newStudent);
	}

	@Override
	public Student getStudent(long studentId) {
		return getStudentFromDB(studentId);
	}

	@Override
	public void updateStudent(long studentId, Student updatedDetails) {
		Student oldDetails = getStudentFromDB(studentId);

		Student newDetails = oldDetails.toBuilder().firstName(updatedDetails.getFirstName())
				.lastName(updatedDetails.getLastName()).age(updatedDetails.getAge()).build();

		repository.save(newDetails);
	}

	@Override
	public void deleteStudent(long studentId) {
		getStudentFromDB(studentId);
		repository.deleteById(studentId);
	}

	private Student getStudentFromDB(long studentId) {
		return repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
	}

}
