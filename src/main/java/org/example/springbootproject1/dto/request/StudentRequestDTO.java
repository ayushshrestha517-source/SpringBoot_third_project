package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentRequestDTO(

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
        String phone,

        @NotBlank(message = "College name is required")
        @Size(max = 150, message = "College name cannot exceed 150 characters")
        String collegeName,

        @NotBlank(message = "Faculty name is required")
        @Size(max = 100, message = "Faculty name cannot exceed 100 characters")
        String facultyName,

        @NotBlank(message = "Semester is required")
        String semester

) {
}