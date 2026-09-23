package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.SubjectRequestDTO;
import org.example.springbootproject1.dto.response.SubjectResponseDTO;
import org.example.springbootproject1.entity.Semester;
import org.example.springbootproject1.entity.Subject;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.SubjectMapper;
import org.example.springbootproject1.repository.SemesterRepository;
import org.example.springbootproject1.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectDAOImpl implements SubjectDAO{
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final SemesterRepository semesterRepository;

    @Override
    public SubjectResponseDTO saveSubject(SubjectRequestDTO dto) {
        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() -> new ResourceNotFoundException("Semester with id " + dto.semesterId() + " not found"));

        if (subjectRepository.existsBySubjectAndSemesterId(dto.subject(),dto.semesterId())) {
            throw new AlreadyExistsException("Subject " + dto.subject()+ " already exists for Semester with id "+ dto.semesterId());
        }

        Subject subject = subjectMapper.toEntity(dto);
        subject.setSemester(semester);

        Subject savedSubject = subjectRepository.save(subject);
        return subjectMapper.toResponseDTO(savedSubject);
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toResponseDTO)
                .toList();
    }

    @Override
    public SubjectResponseDTO getSubjectById(Long id) {

        Subject subject = subjectRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Subject with id " + id + " not found" ));

        return subjectMapper.toResponseDTO(subject);
    }

    @Override
    public List<SubjectResponseDTO> getSubjectsBySemester(Long semesterId) {
            if (!semesterRepository.existsById(semesterId)) {
                throw new ResourceNotFoundException("Semester with id " + semesterId + " not found" );
            }
        List<Subject> subjects = subjectRepository.findBySemesterId(semesterId);
        if (subjects.isEmpty()) {
            throw new ResourceNotFoundException("No subjects found for Semester with id " + semesterId);
        }
            return subjectRepository.findBySemesterId(semesterId)
                    .stream()
                    .map(subjectMapper::toResponseDTO)
                    .toList();
        }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Subject with id " + id + " not found"));

        subjectRepository.delete(subject);
    }

    @Override
    public SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO dto) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Subject with id " + id + " not found"));

        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() ->new ResourceNotFoundException("Semester with id "+ dto.semesterId()+" not found"));

        subject.setSubject(dto.subject());
        subject.setSemester(semester);

        return subjectMapper.toResponseDTO(subjectRepository.save(subject));
    }
}

