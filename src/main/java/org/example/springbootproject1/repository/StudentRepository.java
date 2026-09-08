package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Long> {
    public boolean existsByUserId(Long userId);

}
