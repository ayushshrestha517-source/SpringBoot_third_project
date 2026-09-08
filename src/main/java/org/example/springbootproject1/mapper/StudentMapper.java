package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.StudentResponseDTO;
import org.example.springbootproject1.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "faculty", ignore = true)
    @Mapping(target = "semester", ignore = true)
    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentRequestDTO dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "semester.id", target = "semesterId")
    StudentResponseDTO toResponseDTO(Student student);
}