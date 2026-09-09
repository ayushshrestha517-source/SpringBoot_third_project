package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    public boolean existsByUserId(Long userId);

}
