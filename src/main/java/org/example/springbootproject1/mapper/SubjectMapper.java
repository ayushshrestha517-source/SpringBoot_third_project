package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.SubjectRequestDTO;
import org.example.springbootproject1.dto.response.SubjectResponseDTO;
import org.example.springbootproject1.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(source = "semester.id", target = "semesterId")
    SubjectResponseDTO toResponseDTO(Subject subject);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "semester", ignore = true)
    Subject toEntity(SubjectRequestDTO dto);
}
