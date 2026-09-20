package org.example.springbootproject1.dto.response;

import java.time.LocalDateTime;

public record AssignmentSubmissionResponseDTO(
        Long id,
        Long assignmentId,
        Long studentId,
        String fileUrl,
        LocalDateTime submittedAt
) {
}