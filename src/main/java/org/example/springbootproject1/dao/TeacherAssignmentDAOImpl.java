package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.TeacherAssignmentRequestDTO;
import org.example.springbootproject1.dto.response.StudentListResponseDTO;
import org.example.springbootproject1.dto.response.TeacherAssignedClassResponseDTO;
import org.example.springbootproject1.dto.response.TeacherAssignmentResponseDTO;
import org.example.springbootproject1.entity.*;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.TeacherAssignmentMapper;
import org.example.springbootproject1.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAssignmentDAOImpl implements TeacherAssignmentDAO {

    private final TeacherAssignmentRepository teacherAssignmentRepository;
    private final TeacherRepository teacherRepository;
    private final FacultyRepository facultyRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final TeacherAssignmentMapper teacherAssignmentMapper;

    @Override
    public TeacherAssignmentResponseDTO createAssignment(TeacherAssignmentRequestDTO dto) {

        Teacher teacher = teacherRepository.findById(dto.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Faculty faculty = facultyRepository.findById(dto.facultyId()).orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));
        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new ResourceNotFoundException("Semester does not belong to selected faculty");
        }

        Subject subject = subjectRepository.findById(dto.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        if (!subject.getSemester().getId().equals(semester.getId())) {
            throw new ResourceNotFoundException("Subject does not belong to selected semester");
        }

        boolean alreadyAssigned = teacherAssignmentRepository.existsByTeacherIdAndFacultyIdAndSemesterId(dto.teacherId(), dto.facultyId(), dto.semesterId());

        if (alreadyAssigned) {
            throw new AlreadyExistsException("Teacher is already assigned to this faculty and semester");
        }

        TeacherAssignment assignment = teacherAssignmentMapper.toEntity(dto);

        assignment.setTeacher(teacher);
        assignment.setFaculty(faculty);
        assignment.setSemester(semester);
        assignment.setSubject(subject);

        TeacherAssignment savedAssignment = teacherAssignmentRepository.save(assignment);
        return teacherAssignmentMapper.toResponseDTO(savedAssignment);
    }

    @Override
    public List<TeacherAssignedClassResponseDTO> getAssignedClasses(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        List<TeacherAssignment> assignedClasses = teacherAssignmentRepository.findAllByTeacher_Id(teacherId);

        return assignedClasses.stream().map(assignment->new TeacherAssignedClassResponseDTO(
                assignment.getId(),
                assignment.getFaculty().getId(),
                assignment.getFaculty().getFaculty(),
                assignment.getSemester().getId(),
                assignment.getSemester().getSemester(),
                assignment.getSubject().getId(),
                assignment.getSubject().getSubject(),
                assignment.getSubject().getCourseCode()
        )).toList();
    }

    @Override
    public List<StudentListResponseDTO> getStudents(Long facultyId, Long semesterId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));
        Semester semester = semesterRepository.findById(semesterId).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new ResourceNotFoundException("Semester does not belong to selected faculty");
        }

        List<Student> studentList = studentRepository.findByFaculty_IdAndSemester_Id(facultyId, semesterId);

        return studentList.stream().map(student -> new StudentListResponseDTO(
                student.getId(),
                student.getUser().getFirstName()
        )).toList();
    }
}