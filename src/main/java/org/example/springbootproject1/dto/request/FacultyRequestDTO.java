package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FacultyRequestDTO(

        @NotBlank(message = "Faculty name is required")
        @Size(min = 2, max = 100, message = "Faculty name must be between 2 and 100 characters")
        String faculty

) {
}