package org.example.springbootproject1.dto.response;

import java.time.LocalDate;

public record EventResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate eventDate,
        String color
) {
}