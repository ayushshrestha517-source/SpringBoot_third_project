package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.StudentDAO;
import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.StudentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {
    final StudentDAO studentDAO;

    @PostMapping("/createstudent")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(@Valid @RequestBody  StudentRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(studentDAO.createStudent(dto),"Student assigned successfully."));
    }
}
