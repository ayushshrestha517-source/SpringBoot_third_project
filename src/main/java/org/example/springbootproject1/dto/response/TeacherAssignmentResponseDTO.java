package org.example.springbootproject1.dto.response;

public record TeacherAssignmentResponseDTO(
        Long id,
        Long teacherId,
        Long facultyId,
        Long semesterId,
        Long subjectId
) {
}