package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.AssignmentRequestDTO;
import org.example.springbootproject1.dto.response.AssignmentResponseDTO;
import org.example.springbootproject1.entity.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "faculty", ignore = true)
    @Mapping(target = "semester", ignore = true)
    @Mapping(target = "subject", ignore = true)
    Assignment toEntity(AssignmentRequestDTO dto);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "subject.id", target = "subjectId")
    AssignmentResponseDTO toResponseDTO(Assignment assignment);
}
