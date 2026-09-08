package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.StudentResponseDTO;
import org.example.springbootproject1.entity.Faculty;
import org.example.springbootproject1.entity.Semester;
import org.example.springbootproject1.entity.Student;
import org.example.springbootproject1.entity.Users;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.StudentMapper;
import org.example.springbootproject1.repository.FacultyRepository;
import org.example.springbootproject1.repository.SemesterRepository;
import org.example.springbootproject1.repository.StudentRepository;
import org.example.springbootproject1.repository.UsersRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentDAOImpl implements StudentDAO{
    final UsersRepository usersRepository;
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;
    final FacultyRepository facultyRepository;
    final SemesterRepository semesterRepository;

    @Override
    public StudentResponseDTO  createStudent(StudentRequestDTO dto) {
        Users user = usersRepository.findById(dto.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean isStudent = user.getRoles().stream().anyMatch(role -> "ROLE_STUDENT".equals(role.getName()));

        if (!isStudent) {
            throw new AlreadyExistsException("User does not have ROLE_STUDENT");
        }

        if (studentRepository.existsByUserId(dto.userId())) {
            throw new AlreadyExistsException("Student already exists for this user");
        }

        Faculty faculty = facultyRepository.findById(dto.facultyId())
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));


        Semester semester = semesterRepository.findById(dto.semesterId()).orElseThrow(() -> new ResourceNotFoundException("Semester not found"));
        if (!semester.getFaculty().getId().equals(faculty.getId())) {
            throw new IllegalArgumentException("Semester does not belong to selected faculty");
        }

        Student student = studentMapper.toEntity(dto);
        student.setUser(user);
        student.setFaculty(faculty);
        student.setSemester(semester);

        return studentMapper.toResponseDTO(studentRepository.save(student));
    }

}

