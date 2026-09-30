package org.example.springbootproject1.dao;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.springbootproject1.dto.request.NotificationRequestDTO;

public interface NotificationDAO {
    public String sendNotification(NotificationRequestDTO dto) throws FirebaseMessagingException;
}
