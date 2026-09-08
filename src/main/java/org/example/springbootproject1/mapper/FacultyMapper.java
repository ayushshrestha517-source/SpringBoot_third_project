package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.FacultyRequestDTO;
import org.example.springbootproject1.dto.response.FacultyResponseDTO;
import org.example.springbootproject1.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    @Mapping(target = "id", ignore = true)
    Faculty toEntity(FacultyRequestDTO requestDTO);

    FacultyResponseDTO toResponseDTO(Faculty faculty);

    List<FacultyResponseDTO> toResponseDTOList(List<Faculty> facultyList);
}