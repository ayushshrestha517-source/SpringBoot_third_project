package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.StudentRequestDTO;
import org.example.springbootproject1.dto.response.StudentResponseDTO;
import org.example.springbootproject1.entity.Student;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.StudentMapper;
import org.example.springbootproject1.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentDAOImpl implements StudentDAO{
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;
    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {
        Student student1 = studentRepository.findByEmail(studentRequestDTO.email());
        if(student1!=null){
            throw new AlreadyExistsException("Email ALready Exists");
        }

        Student student = studentMapper.toEntity(studentRequestDTO);

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO findStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            throw new ResourceNotFoundException("Student of entered Id record is not found");
        }
        return studentMapper.toResponseDTO(student.get());
    }

    @Override
    public List<StudentResponseDTO> findStudent() {
        List<Student> studentList = studentRepository.findAll();

        if(studentList.isEmpty()){
            throw new ResourceNotFoundException("No student record is found");
        }

        return studentMapper.toResponseDTOList(studentList);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            throw new ResourceNotFoundException("Student of entered Id record is not found");
        }

        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id,StudentRequestDTO studentRequestDTO) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            throw new ResourceNotFoundException("Student of entered Id record is not found");
        }

        Student existingStudent = student.get();

        existingStudent.setName(studentRequestDTO.name());
        existingStudent.setEmail(studentRequestDTO.email());
        existingStudent.setPhone(studentRequestDTO.phone());
        existingStudent.setCollegeName(studentRequestDTO.collegeName());
        existingStudent.setFacultyName(studentRequestDTO.facultyName());
        existingStudent.setSemester(studentRequestDTO.semester());

        Student updatedStudent = studentRepository.save(existingStudent);

        return studentMapper.toResponseDTO(updatedStudent);
    }


}

