package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;

public interface TeacherAssignmentDAO {
    TeacherAssignmentResponseDTO createAssignment(TeacherAssignmentRequestDTO dto);
}