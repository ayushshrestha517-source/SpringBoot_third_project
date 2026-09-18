package org.example.springbootproject1.repository;

import org.example.springbootproject1.dto.response.AttendanceReportResponseDTO;
import org.example.springbootproject1.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByTeacherIdAndFacultyIdAndSemesterIdAndSubjectIdAndAttendanceDate(
            Long teacherId,
            Long facultyId,
            Long semesterId,
            Long subjectId,
            LocalDate attendanceDate
    );

    @Query("""
    SELECT new org.example.springbootproject1.dto.response.AttendanceReportResponseDTO(
        ad.student.id,
        SUM(CASE WHEN ad.status = org.example.springbootproject1.util.StudentAttendanceStatus.PRESENT THEN 1 ELSE 0 END),
        SUM(CASE WHEN ad.status = org.example.springbootproject1.util.StudentAttendanceStatus.ABSENT THEN 1 ELSE 0 END),
        COUNT(ad.id),
        (SUM(CASE WHEN ad.status = org.example.springbootproject1.util.StudentAttendanceStatus.PRESENT THEN 1 ELSE 0 END) * 100.0 / COUNT(ad.id))
    )
    FROM AttendanceDetail ad
    WHERE ad.student.id = :studentId
    GROUP BY ad.student.id
    """)
    AttendanceReportResponseDTO getAttendanceReport(Long studentId);
}