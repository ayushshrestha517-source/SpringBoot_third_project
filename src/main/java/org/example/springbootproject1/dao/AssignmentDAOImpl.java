package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.AssignmentRequestDTO;
import org.example.springbootproject1.dto.response.AssignmentResponseDTO;
import org.example.springbootproject1.entity.Assignment;
import org.example.springbootproject1.entity.Faculty;
import org.example.springbootproject1.entity.Semester;
import org.example.springbootproject1.entity.Subject;
import org.example.springbootproject1.entity.Teacher;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.AssignmentMapper;
import org.example.springbootproject1.repository.AssignmentRepository;
import org.example.springbootproject1.repository.FacultyRepository;
import org.example.springbootproject1.repository.SemesterRepository;
import org.example.springbootproject1.repository.SubjectRepository;
import org.example.springbootproject1.repository.TeacherAssignmentRepository;
import org.example.springbootproject1.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentDAOImpl implements AssignmentDAO {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    private final TeacherRepository teacherRepository;
    private final TeacherAssignmentRepository teacherAssignmentRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public AssignmentResponseDTO createAssignment(AssignmentRequestDTO dto) {

        Teacher teacher = teacherRepository.findById(dto.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Faculty faculty = facultyRepository.findById(dto.facultyId()).orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        Subject subject = subjectRepository.findById(dto.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        // Check semester belongs to faculty
        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new ResourceNotFoundException("Semester does not belong to selected faculty");
        }

        // Check subject belongs to semester
        if (!subject.getSemester().getId().equals(semester.getId())) {
            throw new ResourceNotFoundException("Subject does not belong to selected semester");
        }

        // Check teacher is assigned to this faculty, semester and subject
        boolean teacherAssigned =
                teacherAssignmentRepository
                        .existsByTeacherIdAndFacultyIdAndSemesterIdAndSubjectId(
                                teacher.getId(),
                                faculty.getId(),
                                semester.getId(), subject.getId()
                        );

        if (!teacherAssigned) {
            throw new ResourceNotFoundException("Teacher is not assigned to the selected faculty, semester and subject");
        }

        Assignment assignment = assignmentMapper.toEntity(dto);

        assignment.setTeacher(teacher);
        assignment.setFaculty(faculty);
        assignment.setSemester(semester);
        assignment.setSubject(subject);

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return assignmentMapper.toResponseDTO(savedAssignment);
    }

    @Override
    public AssignmentResponseDTO getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment of id " + id + " not found"));
        return assignmentMapper.toResponseDTO(assignment);
    }

    @Override
    public List<AssignmentResponseDTO> getAllAssignments() {

        return assignmentRepository.findAll()
                .stream()
                .map(assignmentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment of id " + id + " not found"));
        assignmentRepository.delete(assignment);
    }

    @Override
    public AssignmentResponseDTO updateAssignment(Long id, AssignmentRequestDTO dto) {

        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment of id " + id + " not found"));

        assignment.setTitle(dto.title());
        assignment.setDescription(dto.description());
        assignment.setImageUrl(dto.imageUrl());
        assignment.setDueDate(dto.dueDate());

        Assignment updatedAssignment = assignmentRepository.save(assignment);

        return assignmentMapper.toResponseDTO(updatedAssignment);
    }
}