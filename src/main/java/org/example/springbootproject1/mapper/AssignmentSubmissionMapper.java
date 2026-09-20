package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.AssignmentSubmissionRequestDTO;
import org.example.springbootproject1.dto.response.AssignmentSubmissionResponseDTO;
import org.example.springbootproject1.entity.AssignmentSubmission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentSubmissionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignment", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "submittedAt", ignore = true)
    AssignmentSubmission toEntity(AssignmentSubmissionRequestDTO dto);

    @Mapping(source = "assignment.id", target = "assignmentId")
    @Mapping(source = "student.id", target = "studentId")
    AssignmentSubmissionResponseDTO toResponseDTO(AssignmentSubmission submission);
}