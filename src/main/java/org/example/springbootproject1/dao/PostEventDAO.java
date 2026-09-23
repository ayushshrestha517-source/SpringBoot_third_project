package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.PostEventRequestDTO;
import org.example.springbootproject1.dto.response.PostEventResponseDTO;

import java.util.List;

public interface PostEventDAO {
    PostEventResponseDTO savePostEvent(PostEventRequestDTO dto);

    List<PostEventResponseDTO> getAllPostEvents();

    PostEventResponseDTO getPostEventById(Long id);

    PostEventResponseDTO updatePostEvent(Long id, PostEventRequestDTO dto);

    void deletePostEvent(Long id);
}
