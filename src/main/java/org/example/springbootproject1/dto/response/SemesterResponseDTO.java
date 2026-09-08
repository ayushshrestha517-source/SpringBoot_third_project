package org.example.springbootproject1.dto.response;

public record SemesterResponseDTO(
        Long id,
        String semester,
        Long facultyId
) {
}
