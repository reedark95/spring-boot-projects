package com.api.student.handler;

import java.util.List;

public interface StudentService {

	List<StudentDto> getAllStudents();

	StudentDto addNewStudent(BaseStudentDto newDetails);

	StudentDto getStudent(long studentId);

	void updateStudent(long studentId, BaseStudentDto updateDetails);

	void deleteStudent(long studentId);

}
