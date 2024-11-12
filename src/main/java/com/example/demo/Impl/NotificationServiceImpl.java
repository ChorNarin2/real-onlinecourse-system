package com.example.demo.Impl;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.NotificationRepository;
import com.example.demo.models.Notification;
import com.example.demo.services.NotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Notification notification) {
        notificationRepository.save(notification);
    }

}
