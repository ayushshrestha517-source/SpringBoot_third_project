package org.example.springbootproject1.dto.response;

import java.time.LocalDate;

public record AssignmentResponseDTO(
        Long id,
        String title,
        String description,
        String imageUrl,
        LocalDate dueDate,
        Long teacherId,
        Long facultyId,
        Long semesterId,
        Long subjectId

) {
}