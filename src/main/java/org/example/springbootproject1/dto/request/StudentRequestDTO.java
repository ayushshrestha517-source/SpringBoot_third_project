package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDTO(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "Registration number is required")
        String registrationNumber,

        String rollNumber,

        @NotNull(message = "Faculty ID is required")
        Long facultyId,

        @NotNull(message = "Semester ID is required")
        Long semesterId,

        Boolean active
) {
}