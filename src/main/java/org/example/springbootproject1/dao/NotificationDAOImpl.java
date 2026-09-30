package org.example.springbootproject1.dao;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.example.springbootproject1.dto.request.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationDAOImpl implements NotificationDAO{
    private final FirebaseMessaging firebaseMessaging;

    @Override
    public String sendNotification(NotificationRequestDTO dto) throws FirebaseMessagingException {
        Message message = Message.builder().setToken(dto.fcmToken()).setNotification(Notification.builder().setTitle(dto.title()).setBody(dto.description()).build()).build();

        return firebaseMessaging.send(message);
    }
}
