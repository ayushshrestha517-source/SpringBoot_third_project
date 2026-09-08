package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.StudentDAO;
import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.StudentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    final StudentDAO studentDAO;

    @PostMapping("/savestudent")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> saveStudents(@Valid @RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.ok(ApiResponse.success(studentDAO.saveStudent(studentRequestDTO),"Student saved successfully"));
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> findStudentById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(studentDAO.findStudentById(id),"Student retrieved successfully"));
    }

    @GetMapping("/findall")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> findStudent(){
        return ResponseEntity.ok(ApiResponse.success(studentDAO.findStudent(),"Students retrieved successfully"));
    }

    @DeleteMapping("/deletestudentbyid/{id}")
    public ResponseEntity<StudentResponseDTO> deleteById(@PathVariable Long id){
        studentDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updatestudent/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> updateStudents(@Valid @PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.ok(ApiResponse.success(studentDAO.updateStudent(id,studentRequestDTO),"Student updated successfully"));
    }
}
