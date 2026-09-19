package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.TeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, Long> {
    boolean existsByTeacherIdAndFacultyIdAndSemesterId(Long teacherId, Long facultyId, Long semesterId);
    boolean existsByTeacherIdAndFacultyIdAndSemesterIdAndSubjectId(Long teacherId, Long facultyId, Long semesterId, Long subjectId);
    List<TeacherAssignment> findAllByTeacher_Id(Long teacherId);
}