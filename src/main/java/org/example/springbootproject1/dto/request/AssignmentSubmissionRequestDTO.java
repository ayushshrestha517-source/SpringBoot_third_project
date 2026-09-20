package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssignmentSubmissionRequestDTO(

        @NotNull(message = "Assignment ID is required")
        Long assignmentId,

        @NotNull(message = "Student ID is required")
        Long studentId,

        @NotBlank(message = "File URL is required")
        String fileUrl

) {
}