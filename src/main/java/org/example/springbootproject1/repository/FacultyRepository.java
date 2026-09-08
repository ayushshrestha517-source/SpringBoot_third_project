package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Optional<Faculty> findByFaculty(String faculty);
}
