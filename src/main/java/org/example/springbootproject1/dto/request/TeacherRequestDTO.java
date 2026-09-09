package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record TeacherRequestDTO(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "Employee ID is required")
        String employeeId,

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Qualification is required")
        String qualification,

        @NotNull(message = "Experience years is required")
        @Min(value = 0, message = "Experience years cannot be negative")
        Integer experienceYears,

        @NotBlank(message = "Specialization is required")
        String specialization,

        @NotNull(message = "Joining date is required")
        LocalDate joiningDate
) {
}