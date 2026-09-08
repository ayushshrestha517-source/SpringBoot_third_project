package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.FacultyDAO;
import org.example.springbootproject1.dto.request.FacultyRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.FacultyResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")

public class FacultyController {
    private final FacultyDAO facultyDAO;

    @PostMapping("/savefaculty")
    public ResponseEntity<ApiResponse<FacultyResponseDTO>> saveFaculty(@Valid @RequestBody FacultyRequestDTO facultyRequestDTO){
        return ResponseEntity.ok(ApiResponse.success(facultyDAO.saveFaculty(facultyRequestDTO),"Faculty saved successfully."));
    }

    @GetMapping("/getfaculty")
    public ResponseEntity<ApiResponse<List<FacultyResponseDTO>>> getFaculty(){
        return ResponseEntity.ok(ApiResponse.success(facultyDAO.getFaculty(),"Faculties retrieved successfully."));
    }

    @GetMapping("/getfacultybyid/{id}")
    public ResponseEntity<ApiResponse<FacultyResponseDTO>> getFacultyById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(facultyDAO.getFacultyById(id),"Faculty retrieved successfully."));
    }

    @DeleteMapping("/deletefacultybyid/{id}")
    public ResponseEntity<FacultyResponseDTO> deleteById(@PathVariable Long id){
        facultyDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updatefaculty/{id}")
    public ResponseEntity<ApiResponse<FacultyResponseDTO>> updateFaculty(@Valid @PathVariable Long id,@RequestBody FacultyRequestDTO facultyRequestDTO){
        return ResponseEntity.ok(ApiResponse.success(facultyDAO.updateFaculty(id,facultyRequestDTO),"Faculty updated successfully."));
    }



}
