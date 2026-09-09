package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotNull;

public record TeacherAssignmentRequestDTO(

        @NotNull(message = "Teacher ID is required")
        Long teacherId,

        @NotNull(message = "Faculty ID is required")
        Long facultyId,

        @NotNull(message = "Semester ID is required")
        Long semesterId,

        @NotNull(message = "Subject ID is required")
        Long subjectId
) {
}