package org.example.springbootproject1.dao;

import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.EventRequestDTO;
import org.example.springbootproject1.dto.response.EventResponseDTO;
import org.example.springbootproject1.entity.Event;
import org.example.springbootproject1.exception.ResourceNotFoundException;
import org.example.springbootproject1.mapper.EventMapper;
import org.example.springbootproject1.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventDAOImpl implements EventDAO {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventResponseDTO saveEvent(EventRequestDTO dto) {
        Event event = eventMapper.toEntity(dto);

        return eventMapper.toResponseDTO(eventRepository.save(event));
    }

    @Override
    public List<EventResponseDTO> getEvents() {
        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();

        for(Event e:eventRepository.findAll()){
            eventResponseDTOList.add(eventMapper.toResponseDTO(e));
        }

        return eventResponseDTOList;
    }

    @Override
    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event of id "+id+" not found."));

        return eventMapper.toResponseDTO(event);
    }

    @Override
    public void deleteEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event of id "+id+" not found."));

        eventRepository.deleteById(id);
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventRequestDTO dto) {
        eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event of id "+id+" not found."));
        Event event = eventMapper.toEntity(dto);
        event.setId(id);

        return eventMapper.toResponseDTO(eventRepository.save(event));
    }
}
