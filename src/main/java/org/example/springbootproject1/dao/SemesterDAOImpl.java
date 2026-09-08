package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.SemesterRequestDTO;
import org.example.springbootproject1.dto.response.SemesterResponseDTO;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.entity.Faculty;
import org.example.springbootproject1.entity.Semester;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.SemesterMapper;
import org.example.springbootproject1.repository.FacultyRepository;
import org.example.springbootproject1.repository.SemesterRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SemesterDAOImpl implements SemesterDAO {

    private final SemesterRepository semesterRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterMapper semesterMapper;

    @Override
    public SemesterResponseDTO saveSemester(SemesterRequestDTO dto) {
        Faculty faculty = facultyRepository.findById(dto.facultyId()).orElseThrow(() -> new ResourceNotFoundException("Faculty with id " + dto.facultyId() + " not found"));
        if (semesterRepository.existsBySemesterAndFacultyId(dto.semester(),dto.facultyId())) {
            throw new AlreadyExistsException("Semester " + dto.semester()+ " already exists for Faculty with id "+ dto.facultyId());
        }
        Semester semester = semesterMapper.toEntity(dto);
        semester.setFaculty(faculty);
        Semester savedSemester = semesterRepository.save(semester);
        return semesterMapper.toResponseDTO(savedSemester);
    }

    @Override
    public SemesterResponseDTO getSemesterById(Long id) {
        Semester semester = semesterRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Semester with id " + id + " not found"));
        return semesterMapper.toResponseDTO(semester);
    }

    @Override
    public List<SemesterResponseDTO> getAllSemesters() {
        List<SemesterResponseDTO> response = new ArrayList<>();

        for (Semester semester : semesterRepository.findAll()) {
            response.add(semesterMapper.toResponseDTO(semester));
        }

        return response;
    }

    @Override
    public List<SemesterResponseDTO> getSemestersByFaculty(Long facultyId) {

        if (!facultyRepository.existsById(facultyId)) {
            throw new ResourceNotFoundException("Faculty with id " + facultyId + " not found");
        }

        List<Semester> semesters = semesterRepository.findByFacultyId(facultyId);

        if (semesters.isEmpty()) {
            throw new ResourceNotFoundException("No semesters found for Faculty with id " + facultyId);
        }

        List<SemesterResponseDTO> response = new ArrayList<>();

        for (Semester semester : semesterRepository.findByFacultyId(facultyId)) {
            response.add(semesterMapper.toResponseDTO(semester));
        }

        return response;
    }

    @Override
    public SemesterResponseDTO updateSemester(Long id, SemesterRequestDTO dto) {

        Semester semester = semesterRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Semester with id " + id + " not found"));

        Faculty faculty = facultyRepository.findById(dto.facultyId()).orElseThrow(() -> new ResourceNotFoundException("Faculty with id " + dto.facultyId() + " not found"));

        semester.setSemester(dto.semester());
        semester.setFaculty(faculty);

        Semester updatedSemester = semesterRepository.save(semester);

        return semesterMapper.toResponseDTO(updatedSemester);
    }

    @Override
    public void deleteSemester(Long id) {

        Semester semester = semesterRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Semester with id " + id + " not found"));

        semesterRepository.delete(semester);
    }
}
