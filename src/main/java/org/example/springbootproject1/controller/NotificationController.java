package org.example.springbootproject1.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dao.NotificationDAO;
import org.example.springbootproject1.dto.request.NotificationRequestDTO;
import org.example.springbootproject1.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationDAO notificationDAO;

    @PostMapping("/sendnotification")
    public ResponseEntity<ApiResponse<String>> sendNotification(@Valid @RequestBody NotificationRequestDTO dto) throws FirebaseMessagingException {
        return ResponseEntity.ok(ApiResponse.success(notificationDAO.sendNotification(dto), "Notification successfully sent"));
    }
}
