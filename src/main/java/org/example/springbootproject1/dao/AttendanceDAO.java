package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.AttendanceRequestDTO;
import org.example.springbootproject1.dto.response.AttendanceResponseDTO;

public interface AttendanceDAO {

    AttendanceResponseDTO saveOrUpdateAttendance(AttendanceRequestDTO dto);
}