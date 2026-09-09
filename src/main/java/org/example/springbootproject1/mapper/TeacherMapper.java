package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.TeacherRequestDTO;
import org.example.springbootproject1.dto.response.TeacherResponseDTO;
import org.example.springbootproject1.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Teacher toEntity(TeacherRequestDTO dto);

    @Mapping(source = "user.id", target = "userId")
    TeacherResponseDTO toResponseDTO(Teacher teacher);
}