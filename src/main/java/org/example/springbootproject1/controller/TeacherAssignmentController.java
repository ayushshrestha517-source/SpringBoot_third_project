package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.TeacherAssignmentDAO;
import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TeacherAssignmentController {
    private final TeacherAssignmentDAO teacherAssignmentDAO;

    @PostMapping("/createassignment")
    public ResponseEntity<ApiResponse<TeacherAssignmentResponseDTO>> createAssignment(@Valid @RequestBody TeacherAssignmentRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(teacherAssignmentDAO.createAssignment(dto),"Teacher assigned successfully."));
    }
}
