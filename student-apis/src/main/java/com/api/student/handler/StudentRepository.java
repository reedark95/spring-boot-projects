package com.api.student.handler;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	Optional<StudentEntity> findByFirstNameAndLastNameAndAge(String firstName, String lastName, Integer age);

}
