package com.api.student.handler;

import java.util.List;

public interface StudentService {

	List<Student> getAllStudents();

	Student addNewStudent(Student newStudentDetails);

	Student getStudent(long studentId);

	void updateStudent(long studentId, Student updatedStudentDetails);

	void deleteStudent(long studentId);

}
