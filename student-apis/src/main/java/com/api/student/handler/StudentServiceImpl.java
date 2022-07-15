package com.api.student.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.student.exceptions.NoStudentRecordsException;
import com.api.student.exceptions.StudentAlreadyExistException;
import com.api.student.exceptions.StudentNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository repository;

	private DataMapper mapper;

	public StudentServiceImpl(StudentRepository repository, DataMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentEntity> list = repository.findAll();

		if (list.size() == 0)
			throw new NoStudentRecordsException();

		return mapper.map(list);
	}

	@Override
	public StudentDto addNewStudent(BaseStudentDto newDetails) {
		if (repository.findByFirstNameAndLastNameAndAge(newDetails.getFirstName(), newDetails.getLastName(),
				newDetails.getAge()).isPresent())
			throw new StudentAlreadyExistException();

		return mapper.map(repository.save(mapper.map(newDetails)));
	}

	@Override
	public StudentDto getStudent(long studentId) {
		return mapper.map(getStudentFromDB(studentId));
	}

	@Override
	public void updateStudent(long studentId, BaseStudentDto updateDetails) {
		getStudentFromDB(studentId); // check if exists

		StudentEntity newDetails = new StudentEntity(studentId, updateDetails);
		repository.save(newDetails);
	}

	@Override
	public void deleteStudent(long studentId) {
		getStudentFromDB(studentId);// check if exists

		repository.deleteById(studentId);
	}

	private StudentEntity getStudentFromDB(long studentId) {
		return repository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
	}

}
