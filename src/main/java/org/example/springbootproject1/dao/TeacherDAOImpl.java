package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.TeacherRequestDTO;
import org.example.springbootproject1.dto.response.TeacherResponseDTO;
import org.example.springbootproject1.entity.Teacher;
import org.example.springbootproject1.entity.Users;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.TeacherMapper;
import org.example.springbootproject1.repository.TeacherRepository;
import org.example.springbootproject1.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherDAOImpl implements TeacherDAO {

    final UsersRepository usersRepository;
    final TeacherRepository teacherRepository;
    final TeacherMapper teacherMapper;

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO dto) {

        Users user = usersRepository.findById(dto.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        boolean isTeacher = user.getRoles().stream().anyMatch(role -> "ROLE_TEACHER".equals(role.getName()));

        if (!isTeacher) {
            throw new AlreadyExistsException("User does not have ROLE_TEACHER");
        }
        if (teacherRepository.existsByUserId(dto.userId())) {
            throw new AlreadyExistsException("Teacher already exists for this user");
        }
        Teacher teacher = teacherMapper.toEntity(dto);
        teacher.setUser(user);
        return teacherMapper.toResponseDTO(teacherRepository.save(teacher));
    }
}