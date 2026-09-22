package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailOtpRequestDTO(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email
){
}
