package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {

    boolean existsByAssignmentIdAndStudentId(Long assignmentId, Long studentId);

    Optional<AssignmentSubmission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);

    List<AssignmentSubmission> findAllByStudentId(Long studentId);

    List<AssignmentSubmission> findAllByAssignmentId(Long assignmentId);
}