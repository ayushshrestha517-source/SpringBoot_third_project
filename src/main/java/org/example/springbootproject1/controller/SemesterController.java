package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.SemesterDAO;
import org.example.springbootproject1.dto.request.SemesterRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.FacultyResponseDTO;
import org.example.springbootproject1.dto.response.SemesterResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SemesterController {
    private final SemesterDAO semesterDAO;

    @PostMapping("/savesemester")
    public ResponseEntity<ApiResponse<SemesterResponseDTO>> saveSemester(@Valid @RequestBody SemesterRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(semesterDAO.saveSemester(dto),"Semester saved successfully."));
    }


    @GetMapping("/getsemester")
    public ResponseEntity<ApiResponse<List<SemesterResponseDTO>>> getSemester(){
        return ResponseEntity.ok(ApiResponse.success(semesterDAO.getAllSemesters(),"Semesters retrieved successfully."));
    }

    @GetMapping("/getsemesterbyfaculty/{facultyId}")
    public ResponseEntity<ApiResponse<List<SemesterResponseDTO>>> getSemesterByFaculty(@PathVariable Long facultyId){
        return ResponseEntity.ok(ApiResponse.success(semesterDAO.getSemestersByFaculty(facultyId),"Semesters retrieved successfully."));
    }

    @GetMapping("/getsemesterbyid/{id}")
    public ResponseEntity<ApiResponse<SemesterResponseDTO>> getSemesterById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(semesterDAO.getSemesterById(id),"Semester retrieved successfully."));
    }

    @DeleteMapping("/deletesemesterbyid/{id}")
    public ResponseEntity<SemesterResponseDTO> deleteById(@PathVariable Long id){
        semesterDAO.deleteSemester(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updatesemester/{id}")
    public ResponseEntity<ApiResponse<SemesterResponseDTO>> updateSemester(@PathVariable Long id,@Valid @RequestBody SemesterRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(semesterDAO.updateSemester(id,dto),"Semester updated successfully."));
    }



}
