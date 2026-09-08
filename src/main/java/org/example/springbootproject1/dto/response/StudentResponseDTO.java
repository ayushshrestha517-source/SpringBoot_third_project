package org.example.springbootproject1.dto.response;

public record StudentResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        String collegeName,
        String facultyName,
        String semester
) {
}
