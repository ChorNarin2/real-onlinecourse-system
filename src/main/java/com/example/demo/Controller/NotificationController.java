package com.example.demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.UserRepository;
import com.example.demo.models.Notification;
import com.example.demo.models.User;
import com.example.demo.services.NotificationService;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notifications")
@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @GetMapping("/sendNotification")
    public String sendNotification(@AuthenticationPrincipal User user, @RequestParam String message) {
        if (user == null) {
            return "User not authenticated";
        }
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setMessage(message);

            // Send notification (Save it in the database)
            notificationService.sendNotification(notification);
            return "Notification sent successfully!";
       
    }

}
