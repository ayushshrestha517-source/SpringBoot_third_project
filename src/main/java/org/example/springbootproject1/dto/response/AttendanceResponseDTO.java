package org.example.springbootproject1.dto.response;

import java.time.LocalDate;
import java.util.List;

public record AttendanceResponseDTO(

        Long id,

        LocalDate attendanceDate,

        Long teacherId,

        Long facultyId,

        Long semesterId,

        Long subjectId,

        List<AttendanceDetailResponseDTO> attendanceDetails
) {
}