package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Notification;
import com.example.demo.models.User;

public interface NotificationService {

    // void sendNotification(Notification notification);
    void createNotification(User user, String messages);
    List<Notification> getUnreadNotifications(Integer userId);
    void MarkAsRead(Integer NotificationId);
   
    String sendNotification(User user);

}
