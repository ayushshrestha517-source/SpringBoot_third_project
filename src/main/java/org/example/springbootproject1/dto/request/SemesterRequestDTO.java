package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SemesterRequestDTO(
        @NotBlank(message = "Semester is required")
        @Size(min = 3, max = 20, message = "Semester must be between 3 and 20 characters")
        String semester,

        @NotBlank(message = "Faculty id is required")
        Long facultyId
) {
}
