package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.FacultyRequestDTO;
import org.example.springbootproject1.dto.response.FacultyResponseDTO;
import org.example.springbootproject1.entity.Faculty;

import java.util.List;

public interface FacultyDAO {
    public FacultyResponseDTO saveFaculty(FacultyRequestDTO facultyRequestDTO);
    public List<FacultyResponseDTO> getFaculty();
    public FacultyResponseDTO getFacultyById(Long id);
    public void deleteById(Long id);
    public FacultyResponseDTO updateFaculty(Long id, FacultyRequestDTO facultyRequestDTO);
}
