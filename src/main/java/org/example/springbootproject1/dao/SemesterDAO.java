package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.SemesterRequestDTO;
import org.example.springbootproject1.dto.response.SemesterResponseDTO;

import java.util.List;

public interface SemesterDAO {

    SemesterResponseDTO saveSemester(SemesterRequestDTO semesterRequestDTO);

    SemesterResponseDTO getSemesterById(Long id);

    List<SemesterResponseDTO> getAllSemesters();

    List<SemesterResponseDTO> getSemestersByFaculty(Long facultyId);

    SemesterResponseDTO updateSemester(Long id,SemesterRequestDTO semesterRequestDTO);

    void deleteSemester(Long id);
}
