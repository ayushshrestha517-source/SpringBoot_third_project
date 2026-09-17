package org.example.springbootproject1.dto.response;

import org.example.springbootproject1.util.StudentAttendanceStatus;

public record AttendanceDetailResponseDTO(

        Long id,

        Long studentId,

        StudentAttendanceStatus status
) {
}