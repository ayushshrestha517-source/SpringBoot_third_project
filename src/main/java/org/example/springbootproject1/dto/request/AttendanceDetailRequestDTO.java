package org.example.springbootproject1.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.springbootproject1.util.StudentAttendanceStatus;

public record AttendanceDetailRequestDTO(

        @NotNull(message = "Student ID is required")
        Long studentId,

        @NotNull(message = "Attendance status is required")
        StudentAttendanceStatus status
) {
}
