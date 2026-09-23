package org.example.springbootproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.PostEventDAO;
import org.example.springbootproject1.dto.request.PostEventRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.example.springbootproject1.dto.response.PostEventResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PostEventController {

    private final PostEventDAO postEventDAO;

    @PostMapping("/savepostevent")
    public ResponseEntity<ApiResponse<PostEventResponseDTO>> savePostEvent(@Valid @RequestBody PostEventRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(postEventDAO.savePostEvent(dto), "Post event successfully created"));
    }

    @GetMapping("/getpostevents")
    public ResponseEntity<ApiResponse<List<PostEventResponseDTO>>> getAllPostEvents() {
        return ResponseEntity.ok(ApiResponse.success(postEventDAO.getAllPostEvents(), "Post events successfully fetched"));
    }

    @GetMapping("/getposteventbyid/{id}")
    public ResponseEntity<ApiResponse<PostEventResponseDTO>> getPostEventById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(postEventDAO.getPostEventById(id), "Post event successfully fetched"));
    }

    @PutMapping("/updatepostevent/{id}")
    public ResponseEntity<ApiResponse<PostEventResponseDTO>> updatePostEvent(@PathVariable Long id, @Valid @RequestBody PostEventRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(postEventDAO.updatePostEvent(id, dto), "Post event successfully updated"));
    }

    @DeleteMapping("/deletepostevent/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostEvent(@PathVariable Long id) {
        postEventDAO.deletePostEvent(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Post event successfully deleted"));
    }
}