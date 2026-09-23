package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.EventDAO;
import org.example.springbootproject1.dto.request.EventRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.EventResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EventController {
    private final EventDAO eventDAO;

    @PostMapping("/saveevent")
    public ResponseEntity<ApiResponse<EventResponseDTO>> saveEvent(@Valid @RequestBody EventRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(eventDAO.saveEvent(dto),"Event saved successfully."));
    }

    @GetMapping("/getevent")
    public ResponseEntity<ApiResponse<List<EventResponseDTO>>> getEvents(){
        return ResponseEntity.ok(ApiResponse.success(eventDAO.getEvents(),"Events retrieved successfully."));
    }

    @GetMapping("/geteventbyid/{id}")
    public ResponseEntity<ApiResponse<EventResponseDTO>> getEventById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(eventDAO.getEventById(id),"Event retrieved successfully."));
    }

    @DeleteMapping("/deleteeventbyid/{id}")
    public ResponseEntity<EventResponseDTO> deleteEventById(@PathVariable Long id){
        eventDAO.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateevent/{id}")
    public ResponseEntity<ApiResponse<EventResponseDTO>> updateEvent(@PathVariable Long id,@Valid @RequestBody EventRequestDTO dto){
        return ResponseEntity.ok(ApiResponse.success(eventDAO.updateEvent(id,dto),"Event updated successfully."));
    }
}
