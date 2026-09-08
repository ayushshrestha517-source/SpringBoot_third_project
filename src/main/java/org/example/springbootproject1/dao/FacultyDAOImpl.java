package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.FacultyRequestDTO;
import org.example.springbootproject1.dto.response.FacultyResponseDTO;
import org.example.springbootproject1.entity.Faculty;
import org.example.springbootproject1.exception.AlreadyExistsException;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.FacultyMapper;
import org.example.springbootproject1.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyDAOImpl implements FacultyDAO{
    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    @Override
    public FacultyResponseDTO saveFaculty(FacultyRequestDTO facultyRequestDTO) {
        if(facultyRepository.findByFaculty(facultyRequestDTO.faculty()).isPresent()){
            throw new AlreadyExistsException("Faculty already exists.");
        }

        Faculty faculty = facultyMapper.toEntity(facultyRequestDTO);
        Faculty savedFaculty = facultyRepository.save(faculty);

        return facultyMapper.toResponseDTO(savedFaculty);
    }

    @Override
    public List<FacultyResponseDTO> getFaculty() {
        List<Faculty> facultyList = facultyRepository.findAll();

        if(facultyList.isEmpty()){
            throw new ResourceNotFoundException("No data found.");
        }

        return facultyMapper.toResponseDTOList(facultyList);
    }

    @Override
    public FacultyResponseDTO getFacultyById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        if(faculty.isEmpty()){
            throw new ResourceNotFoundException("No data found.");
        }

        return facultyMapper.toResponseDTO(faculty.get());
    }

    @Override
    public void deleteById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        if(faculty.isEmpty()){
            throw new ResourceNotFoundException("No data found.");
        }

        facultyRepository.deleteById(id);
    }

    @Override
    public FacultyResponseDTO updateFaculty(Long id, FacultyRequestDTO facultyRequestDTO) {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        if(faculty.isEmpty()){
            throw new ResourceNotFoundException("No data found.");
        }

        Faculty existingFaculty = faculty.get();

        existingFaculty.setFaculty(facultyRequestDTO.faculty());

        Faculty updatedFaculty = facultyRepository.save(existingFaculty);

        return facultyMapper.toResponseDTO(updatedFaculty);
    }



}
