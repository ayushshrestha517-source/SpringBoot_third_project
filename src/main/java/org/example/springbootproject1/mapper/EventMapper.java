package org.example.springbootproject1.mapper;

import org.example.springbootproject1.dto.request.EventRequestDTO;
import org.example.springbootproject1.dto.response.EventResponseDTO;
import org.example.springbootproject1.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    Event toEntity(EventRequestDTO dto);

    EventResponseDTO toResponseDTO(Event event);
}