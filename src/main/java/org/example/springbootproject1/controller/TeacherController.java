package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.TeacherDAO;
import org.example.springbootproject1.dto.request.TeacherRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.TeacherResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TeacherController {
    private final TeacherDAO teacherDAO;

    @PostMapping("/createteacher")
    public ResponseEntity<ApiResponse<TeacherResponseDTO>> createTeacher(@Valid @RequestBody TeacherRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(teacherDAO.createTeacher(dto),"Teacher created successfully."));
    }
}
