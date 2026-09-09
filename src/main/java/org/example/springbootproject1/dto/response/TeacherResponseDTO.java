package org.example.springbootproject1.dto.response;

import java.time.LocalDate;

public record TeacherResponseDTO(
        Long id,
        Long userId,
        String employeeId,
        String phoneNumber,
        String qualification,
        Integer experienceYears,
        String specialization,
        LocalDate joiningDate
) {
}