package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;
import org.example.springbootproject1.entity.Faculty;
import org.example.springbootproject1.entity.Semester;
import org.example.springbootproject1.entity.Subject;
import org.example.springbootproject1.entity.Teacher;
import org.example.springbootproject1.entity.TeacherAssignment;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.TeacherAssignmentMapper;
import org.example.springbootproject1.repository.FacultyRepository;
import org.example.springbootproject1.repository.SemesterRepository;
import org.example.springbootproject1.repository.SubjectRepository;
import org.example.springbootproject1.repository.TeacherAssignmentRepository;
import org.example.springbootproject1.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherAssignmentDAOImpl implements TeacherAssignmentDAO {

    private final TeacherAssignmentRepository teacherAssignmentRepository;
    private final TeacherRepository teacherRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherAssignmentMapper teacherAssignmentMapper;

    @Override
    public TeacherAssignmentResponseDTO createAssignment(TeacherAssignmentRequestDTO dto) {

        // 1. Check teacher
        Teacher teacher = teacherRepository.findById(dto.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        // 2. Check faculty
        Faculty faculty = facultyRepository.findById(dto.facultyId()).orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        // 3. Check semester
        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        // 4. Check semester belongs to faculty
        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new ResourceNotFoundException("Semester does not belong to selected faculty");
        }

        // 5. Check subject
        Subject subject = subjectRepository.findById(dto.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        // 6. Check subject belongs to semester
        if (!subject.getSemester().getId().equals(semester.getId())) {
            throw new ResourceNotFoundException("Subject does not belong to selected semester");
        }

        // 7. Check teacher is not already assigned to this faculty + semester
        boolean alreadyAssigned = teacherAssignmentRepository.existsByTeacherIdAndFacultyIdAndSemesterId(dto.teacherId(), dto.facultyId(), dto.semesterId());

        if (alreadyAssigned) {
            throw new AlreadyExistsException("Teacher is already assigned to this faculty and semester");
        }

        // 8. Convert DTO to entity
        TeacherAssignment assignment = teacherAssignmentMapper.toEntity(dto);

        // 9. Set relationships
        assignment.setTeacher(teacher);
        assignment.setFaculty(faculty);
        assignment.setSemester(semester);
        assignment.setSubject(subject);

        // 10. Save
        TeacherAssignment savedAssignment = teacherAssignmentRepository.save(assignment);

        // 11. Return response DTO
        return teacherAssignmentMapper.toResponseDTO(savedAssignment);
    }
}