package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.SemesterRequestDTO;
import org.example.springbootproject1.dto.response.SemesterResponseDTO;
import org.example.springbootproject1.entity.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface SemesterMapper {
    @Mapping(source = "faculty.id", target = "facultyId")
    SemesterResponseDTO toResponseDTO(Semester semester);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "faculty", ignore = true)
    Semester toEntity(SemesterRequestDTO dto);
}
