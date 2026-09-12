package org.example.springbootproject1.dao;

import org.example.springbootproject1.dto.request.EventRequestDTO;
import org.example.springbootproject1.dto.response.EventResponseDTO;

import java.util.List;

public interface EventDAO {
    public EventResponseDTO saveEvent(EventRequestDTO dto);
    public List<EventResponseDTO> getEvents();
    public EventResponseDTO getEventById(Long id);
    public void deleteEventById(Long id);
    public EventResponseDTO updateEvent(Long id,EventRequestDTO dto);
}
