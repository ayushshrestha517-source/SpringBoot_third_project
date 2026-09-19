package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.AssignmentRequestDTO;
import org.example.springbootproject1.dto.response.AssignmentResponseDTO;

import java.util.List;

public interface AssignmentDAO {
    AssignmentResponseDTO createAssignment(AssignmentRequestDTO dto);
    AssignmentResponseDTO getAssignmentById(Long id);
    List<AssignmentResponseDTO> getAllAssignments();
    void deleteAssignmentById(Long id);
    public AssignmentResponseDTO updateAssignment(Long id, AssignmentRequestDTO dto);
}
