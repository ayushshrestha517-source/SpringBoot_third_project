package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.PostEventRequestDTO;
import org.example.springbootproject1.dto.response.PostEventResponseDTO;
import org.example.springbootproject1.entity.PostEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostEventMapper {

    PostEvent toEntity(PostEventRequestDTO dto);

    PostEventResponseDTO toResponseDTO(PostEvent postEvent);
}