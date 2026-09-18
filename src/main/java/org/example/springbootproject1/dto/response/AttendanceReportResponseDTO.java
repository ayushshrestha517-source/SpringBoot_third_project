package org.example.springbootproject1.dto.response;

public record AttendanceReportResponseDTO(
        Long studentId,
        long presentDays,
        long absentDays,
        long totalDays,
        double attendancePercentage
) {
}
