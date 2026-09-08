package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.StudentResponseDTO;

import java.util.List;

public interface StudentDAO {
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO);
    public StudentResponseDTO findStudentById(Long id);
    public List<StudentResponseDTO> findStudent();
    public void deleteById(Long id);
    public StudentResponseDTO updateStudent(Long id,StudentRequestDTO studentRequestDTO);
}
