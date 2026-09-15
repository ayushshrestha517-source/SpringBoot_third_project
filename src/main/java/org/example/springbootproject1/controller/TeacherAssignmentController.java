package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.TeacherAssignmentDAO;
import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.StudentListResponseDTO;
import org.example.springbootproject1.dto.response.TeacherAssignedClassResponseDTO;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherAssignmentController {
    private final TeacherAssignmentDAO teacherAssignmentDAO;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createassignment")
    public ResponseEntity<ApiResponse<TeacherAssignmentResponseDTO>> createAssignment(@Valid @RequestBody TeacherAssignmentRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(teacherAssignmentDAO.createAssignment(dto),"Teacher assigned successfully."));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/getassignment/{teacherId}")
    public ResponseEntity<ApiResponse<List<TeacherAssignedClassResponseDTO>>> getAssignedClasses(@PathVariable Long teacherId){
        return ResponseEntity.ok(ApiResponse.success(teacherAssignmentDAO.getAssignedClasses(teacherId),"Assigned classes successfully fetched"));
    }


    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/getstudentlist")
    public ResponseEntity<ApiResponse<List<StudentListResponseDTO>>> getStudents(@RequestParam Long facultyId, @RequestParam Long semesterId){
        return ResponseEntity.ok(ApiResponse.success(teacherAssignmentDAO.getStudents(facultyId,semesterId),"Students successfully fetched for attendance"));
    }
}
