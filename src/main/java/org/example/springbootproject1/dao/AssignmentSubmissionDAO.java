package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.AssignmentSubmissionRequestDTO;
import org.example.springbootproject1.dto.request.AssignmentSubmissionUpdateRequestDTO;
import org.example.springbootproject1.dto.response.AssignmentSubmissionResponseDTO;

import java.util.List;

public interface AssignmentSubmissionDAO {
    AssignmentSubmissionResponseDTO submitAssignment(AssignmentSubmissionRequestDTO dto);
    AssignmentSubmissionResponseDTO getSubmission(Long assignmentId, Long studentId);
    List<AssignmentSubmissionResponseDTO> getStudentSubmissions(Long studentId);
    List<AssignmentSubmissionResponseDTO> getAssignmentSubmissions(Long assignmentId);
    AssignmentSubmissionResponseDTO updateSubmission(Long id, AssignmentSubmissionUpdateRequestDTO dto);
}