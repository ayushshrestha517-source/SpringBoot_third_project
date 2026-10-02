package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.AssignmentSubmissionDAO;
import org.example.springbootproject1.dto.request.AssignmentSubmissionRequestDTO;
import org.example.springbootproject1.dto.request.AssignmentSubmissionUpdateRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.AssignmentSubmissionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment-submission")
@RequiredArgsConstructor
public class AssignmentSubmissionController {

    private final AssignmentSubmissionDAO assignmentSubmissionDAO;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<AssignmentSubmissionResponseDTO>> submitAssignment(@Valid @RequestBody AssignmentSubmissionRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(assignmentSubmissionDAO.submitAssignment(dto), "Assignment submitted successfully"));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/{assignmentId}/{studentId}")
    public ResponseEntity<ApiResponse<AssignmentSubmissionResponseDTO>> getSubmission(@PathVariable Long assignmentId, @PathVariable Long studentId) {
        return ResponseEntity.ok(ApiResponse.success(assignmentSubmissionDAO.getSubmission(assignmentId, studentId), "Submission successfully fetched"));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<AssignmentSubmissionResponseDTO>>> getStudentSubmissions(@PathVariable Long studentId) {
        return ResponseEntity.ok(ApiResponse.success(assignmentSubmissionDAO.getStudentSubmissions(studentId), "Student submissions successfully fetched"));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<ApiResponse<List<AssignmentSubmissionResponseDTO>>> getAssignmentSubmissions(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(ApiResponse.success(assignmentSubmissionDAO.getAssignmentSubmissions(assignmentId), "Assignment submissions successfully fetched"));
    }


    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssignmentSubmissionResponseDTO>> updateSubmission(@PathVariable Long id, @Valid @RequestBody AssignmentSubmissionUpdateRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(assignmentSubmissionDAO.updateSubmission(id, dto), "Assignment submission successfully updated"));
    }

}