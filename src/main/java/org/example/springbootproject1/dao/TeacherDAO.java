package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.TeacherRequestDTO;
import org.example.springbootproject1.dto.response.TeacherResponseDTO;

public interface TeacherDAO {
    public TeacherResponseDTO createTeacher(TeacherRequestDTO dto);
}
