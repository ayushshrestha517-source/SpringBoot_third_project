package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AssignmentSubmissionUpdateRequestDTO(

        @NotBlank(message = "File URL is required")
        String fileUrl

) {
}