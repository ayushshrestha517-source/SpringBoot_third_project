package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findBySemesterId(Long semesterId);
    boolean existsBySubjectAndSemesterId(String subject, Long semesterId);
}
