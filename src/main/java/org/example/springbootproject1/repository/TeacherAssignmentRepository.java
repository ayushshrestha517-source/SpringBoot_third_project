package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.TeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, Long> {
    boolean existsByTeacherIdAndFacultyIdAndSemesterId(Long teacherId, Long facultyId, Long semesterId);
}