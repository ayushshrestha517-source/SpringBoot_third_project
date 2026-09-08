package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.SubjectDAO;
import org.example.springbootproject1.dto.request.SubjectRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.SubjectResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SubjectController {
    private final SubjectDAO subjectDAO;

    @PostMapping("/savesubject")
    public ResponseEntity<ApiResponse<SubjectResponseDTO>> saveSubject(@Valid @RequestBody  SubjectRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(subjectDAO.saveSubject(dto),"Subject saved successfully."));
    }

    @GetMapping("/getsubject")
    public ResponseEntity<ApiResponse<List<SubjectResponseDTO>>> getSubject(){
        return ResponseEntity.ok(ApiResponse.success(subjectDAO.getAllSubjects(),"Subjects retrieved successfully."));
    }

    @GetMapping("/getsubjectbysemester/{semesterId}")
    public ResponseEntity<ApiResponse<List<SubjectResponseDTO>>> getSubjectBySemester(@PathVariable Long semesterId){
        return ResponseEntity.ok(ApiResponse.success(subjectDAO.getSubjectsBySemester(semesterId),"Subjects retrieved successfully."));
    }

    @GetMapping("/getsubjectbyid/{id}")
    public ResponseEntity<ApiResponse<SubjectResponseDTO>> getSubjectById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(subjectDAO.getSubjectById(id),"Subject retrieved successfully."));
    }

    @DeleteMapping("/deletesubject/{id}")
    public ResponseEntity<SubjectResponseDTO> deleteSubject(@PathVariable Long id){
        subjectDAO.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updatesubject/{id}")
    public ResponseEntity<ApiResponse<SubjectResponseDTO>> updateSubject(@Valid @PathVariable Long id, @RequestBody SubjectRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(subjectDAO.updateSubject(id,dto),"Subject updated successfully."));
    }
}

