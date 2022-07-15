package com.api.student.handler;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByFirstNameAndLastNameAndAge(String firstName, String lastName, Integer age);

}
