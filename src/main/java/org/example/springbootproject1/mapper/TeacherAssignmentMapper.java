package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;
import org.example.springbootproject1.entity.TeacherAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherAssignmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "faculty", ignore = true)
    @Mapping(target = "semester", ignore = true)
    @Mapping(target = "subject", ignore = true)
    TeacherAssignment toEntity(TeacherAssignmentRequestDTO dto);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "faculty.id", target = "facultyId")
    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "subject.id", target = "subjectId")
    TeacherAssignmentResponseDTO toResponseDTO(TeacherAssignment assignment);
}