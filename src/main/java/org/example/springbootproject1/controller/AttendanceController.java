package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.AttendanceDAO;
import org.example.springbootproject1.dto.request.AttendanceRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.AttendanceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
public class AttendanceController {

    private final AttendanceDAO attendanceDAO;

    @PostMapping("/saveattendance")
    public ResponseEntity<ApiResponse<AttendanceResponseDTO>> saveAttendance(@Valid @RequestBody AttendanceRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(attendanceDAO.saveOrUpdateAttendance(dto), "Attendance saved/updated successfully."));
    }
}