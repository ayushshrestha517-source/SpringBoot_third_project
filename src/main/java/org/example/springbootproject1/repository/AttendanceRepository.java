package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

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
}