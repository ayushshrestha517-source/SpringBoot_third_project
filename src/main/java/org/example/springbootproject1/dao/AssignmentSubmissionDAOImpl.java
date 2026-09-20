package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.AssignmentSubmissionRequestDTO;
import org.example.springbootproject1.dto.response.AssignmentSubmissionResponseDTO;
import org.example.springbootproject1.entity.Assignment;
import org.example.springbootproject1.entity.AssignmentSubmission;
import org.example.springbootproject1.entity.Student;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.AssignmentSubmissionMapper;
import org.example.springbootproject1.repository.AssignmentRepository;
import org.example.springbootproject1.repository.AssignmentSubmissionRepository;
import org.example.springbootproject1.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionDAOImpl implements AssignmentSubmissionDAO {

    private final AssignmentSubmissionRepository assignmentSubmissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final AssignmentSubmissionMapper assignmentSubmissionMapper;

    @Override
    @Transactional
    public AssignmentSubmissionResponseDTO submitAssignment(AssignmentSubmissionRequestDTO dto) {

        Assignment assignment = assignmentRepository.findById(dto.assignmentId()).orElseThrow(() -> new ResourceNotFoundException("Assignment of id " + dto.assignmentId() + " not found"));
        Student student = studentRepository.findById(dto.studentId()).orElseThrow(() -> new ResourceNotFoundException("Student of id " + dto.studentId() + " not found"));

        // Check student belongs to assignment's faculty
        if (!student.getFaculty().getId().equals(assignment.getFaculty().getId())) {
            throw new ResourceNotFoundException("Student does not belong to the assignment faculty");
        }

        // Check student belongs to assignment's semester
        if (!student.getSemester().getId().equals(assignment.getSemester().getId())) {
            throw new ResourceNotFoundException("Student does not belong to the assignment semester");
        }

        // Check if already submitted
        if (assignmentSubmissionRepository.existsByAssignmentIdAndStudentId(assignment.getId(), student.getId())) {
            throw new IllegalStateException("Student has already submitted this assignment");
        }

        AssignmentSubmission submission = assignmentSubmissionMapper.toEntity(dto);

        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setSubmittedAt(LocalDateTime.now());

        AssignmentSubmission savedSubmission = assignmentSubmissionRepository.save(submission);

        return assignmentSubmissionMapper.toResponseDTO(savedSubmission);
    }

    @Override
    public AssignmentSubmissionResponseDTO getSubmission(Long assignmentId, Long studentId) {
        AssignmentSubmission submission = assignmentSubmissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId).orElseThrow(() -> new ResourceNotFoundException("Submission not found"));
        return assignmentSubmissionMapper.toResponseDTO(submission);
    }

    @Override
    public List<AssignmentSubmissionResponseDTO> getStudentSubmissions(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student of id " + studentId + " not found");
        }

        return assignmentSubmissionRepository
                .findAllByStudentId(studentId)
                .stream()
                .map(assignmentSubmissionMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<AssignmentSubmissionResponseDTO> getAssignmentSubmissions(Long assignmentId) {

        if (!assignmentRepository.existsById(assignmentId)) {
            throw new ResourceNotFoundException("Assignment of id " + assignmentId + " not found");
        }

        return assignmentSubmissionRepository
                .findAllByAssignmentId(assignmentId)
                .stream()
                .map(assignmentSubmissionMapper::toResponseDTO)
                .toList();
    }
}