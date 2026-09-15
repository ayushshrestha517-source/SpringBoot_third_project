package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {
    public boolean existsByUserId(Long userId);
    List<Student> findByFaculty_IdAndSemester_Id(Long facultyId, Long SemesterId);
}
