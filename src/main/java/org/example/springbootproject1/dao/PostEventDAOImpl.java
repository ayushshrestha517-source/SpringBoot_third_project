package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.PostEventRequestDTO;
import org.example.springbootproject1.dto.response.PostEventResponseDTO;
import org.example.springbootproject1.entity.PostEvent;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.PostEventMapper;
import org.example.springbootproject1.repository.PostEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostEventDAOImpl implements PostEventDAO {

    private final PostEventRepository postEventRepository;
    private final PostEventMapper postEventMapper;

    @Override
    public PostEventResponseDTO savePostEvent(PostEventRequestDTO dto) {
        PostEvent postEvent = postEventMapper.toEntity(dto);

        postEvent.setUploadDate(LocalDate.now());
        return postEventMapper.toResponseDTO(postEventRepository.save(postEvent));
    }

    @Override
    public List<PostEventResponseDTO> getAllPostEvents() {
        return postEventRepository.findAll()
                .stream()
                .map(postEventMapper::toResponseDTO)
                .toList();
    }

    @Override
    public PostEventResponseDTO getPostEventById(Long id) {
        PostEvent postEvent = postEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post event of id " + id + " not found"));

        return postEventMapper.toResponseDTO(postEvent);
    }

    @Override
    public PostEventResponseDTO updatePostEvent(Long id, PostEventRequestDTO dto) {

        PostEvent postEvent = postEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post event of id " + id + " not found"));

        postEvent.setTitle(dto.title());
        postEvent.setDescription(dto.description());
        postEvent.setImageUrl(dto.imageUrl());

        postEvent.setUpdatedDate(LocalDate.now());
        return postEventMapper.toResponseDTO(postEventRepository.save(postEvent)
        );
    }

    @Override
    public void deletePostEvent(Long id) {

        PostEvent postEvent = postEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post event of id " + id + " not found"));

        postEventRepository.delete(postEvent);
    }
}