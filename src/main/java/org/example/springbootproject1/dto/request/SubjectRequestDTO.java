package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubjectRequestDTO(

        @NotBlank(message = "Subject is required")
        @Size(min = 2, max = 100, message = "Subject must be between 2 and 100 characters")
        String subject,

        @NotBlank(message = "Course code is required")
        @Size(min = 2, max = 10, message = "Course code must be between 2 and 100 characters")
        String courseCode,
        @NotNull(message = "Semester id is required")
        Long semesterId
) {
}
