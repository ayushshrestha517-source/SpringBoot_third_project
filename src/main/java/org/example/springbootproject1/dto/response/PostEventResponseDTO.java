package org.example.springbootproject1.dto.response;

import java.time.LocalDate;

public record PostEventResponseDTO(
        Long id,
        String title,
        String description,
        String imageUrl,
        LocalDate uploadDate,
        LocalDate updatedDate
) {
}