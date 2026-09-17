package org.example.springbootproject1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record AttendanceRequestDTO(

        @NotNull(message = "Attendance date is required")
        LocalDate attendanceDate,

        @NotNull(message = "Teacher ID is required")
        Long teacherId,

        @NotNull(message = "Faculty ID is required")
        Long facultyId,

        @NotNull(message = "Semester ID is required")
        Long semesterId,

        @NotNull(message = "Subject ID is required")
        Long subjectId,

        @NotEmpty(message = "Attendance details cannot be empty")
        List<@Valid AttendanceDetailRequestDTO> attendanceDetails
) {
}
