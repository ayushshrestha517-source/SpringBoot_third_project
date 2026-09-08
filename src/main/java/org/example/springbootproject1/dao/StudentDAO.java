package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.StudentResponseDTO;

import java.util.List;

public interface StudentDAO {
    public StudentResponseDTO createStudent(StudentRequestDTO dto);
}
