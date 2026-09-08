package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.SubjectRequestDTO;
import org.example.springbootproject1.dto.response.SubjectResponseDTO;

import java.util.List;

public interface SubjectDAO {
    public SubjectResponseDTO saveSubject(SubjectRequestDTO dto);

    public List<SubjectResponseDTO> getAllSubjects();

    public SubjectResponseDTO getSubjectById(Long id);

    public List<SubjectResponseDTO> getSubjectsBySemester(Long semesterId);

    public void deleteSubject(Long id);

    public SubjectResponseDTO updateSubject(Long id,SubjectRequestDTO dto);
}
