package org.example.springbootproject1.dto.response;

public record StudentResponseDTO(
        Long id,
        Long userId,
        String registrationNumber,
        String rollNumber,
        Long facultyId,
        Long semesterId,
        Boolean active
) {
}