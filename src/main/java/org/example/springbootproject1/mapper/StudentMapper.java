package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.StudentResponseDTO;
import org.example.springbootproject1.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDTO dto);

    StudentResponseDTO toResponseDTO(Student student);

    List<StudentResponseDTO> toResponseDTOList(List<Student> students);
}
