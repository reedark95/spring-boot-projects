package com.api.student.handler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private Integer age;

	public StudentEntity(Long id, BaseStudentDto updateDetails) {
		this.id = id;

		this.firstName = updateDetails.getFirstName();
		this.lastName = updateDetails.getLastName();
		this.age = updateDetails.getAge();
	}

}
