package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
    List<Semester> findByFacultyId(Long facultyId);
    boolean existsBySemesterAndFacultyId(String semester, Long facultyId);
}
