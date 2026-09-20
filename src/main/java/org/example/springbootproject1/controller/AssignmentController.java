package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.AssignmentDAO;
import org.example.springbootproject1.dto.request.AssignmentRequestDTO;
import org.example.springbootproject1.dto.request.AssignmentSubmissionUpdateRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.AssignmentResponseDTO;
import org.example.springbootproject1.dto.response.AssignmentSubmissionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
public class AssignmentController {

    private final AssignmentDAO assignmentDAO;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AssignmentResponseDTO>> createAssignment(@Valid @RequestBody AssignmentRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(assignmentDAO.createAssignment(dto), "Assignment successfully created"));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponseDTO>> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(assignmentDAO.getAssignmentById(id), "Assignment successfully fetched"));
    }

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<AssignmentResponseDTO>>> getAllAssignments() {
        return ResponseEntity.ok(ApiResponse.success(assignmentDAO.getAllAssignments(), "Assignments successfully fetched"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAssignmentById(@PathVariable Long id) {
        assignmentDAO.deleteAssignmentById(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Assignment successfully deleted"));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponseDTO>> updateAssignment(@PathVariable Long id, @Valid @RequestBody AssignmentRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(assignmentDAO.updateAssignment(id, dto), "Assignment successfully updated"));
    }
}