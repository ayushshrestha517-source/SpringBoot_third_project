package org.example.springbootproject1.dto.response;

public record SubjectResponseDTO(
        Long id,
        String subject,
        String courseCode,
        Long semesterId
) {
}
