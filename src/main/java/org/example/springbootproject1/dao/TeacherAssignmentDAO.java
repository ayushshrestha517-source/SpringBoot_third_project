package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.StudentListResponseDTO;
import org.example.springbootproject1.dto.response.TeacherAssignedClassResponseDTO;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;

import java.util.List;

public interface TeacherAssignmentDAO {
    TeacherAssignmentResponseDTO createAssignment(TeacherAssignmentRequestDTO dto);
    List<TeacherAssignedClassResponseDTO> getAssignedClasses(Long teacherId);
    List<StudentListResponseDTO> getStudents(Long facultyId, Long semesterId);
}