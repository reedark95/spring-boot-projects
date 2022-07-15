package com.api.student.handler;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataMapper {

	@Mapping(target = "id", ignore = true)
	StudentEntity map(BaseStudentDto dto);

	StudentEntity map(StudentDto dto);

	StudentDto map(StudentEntity entity);

	List<StudentDto> map(List<StudentEntity> list);

}
