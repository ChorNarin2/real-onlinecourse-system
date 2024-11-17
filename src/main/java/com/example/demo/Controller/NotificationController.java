package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.UserRepository;
import com.example.demo.models.Notification;
import com.example.demo.models.User;
import com.example.demo.services.NotificationService;


import lombok.RequiredArgsConstructor;

@RequestMapping("/notifications")
@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/CreateNotification")
    public String sendNotification(@AuthenticationPrincipal User user, @RequestParam String message) {
        if (user == null) {
            return "User not authenticated";
        }
        notificationService.createNotification(user, message);
        return "Notification sent successfully";
       
    }


    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications(@AuthenticationPrincipal User user) {
        return notificationService.getUnreadNotifications(user.getId());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/getNotifications")
    public ResponseEntity<?> StudentGetNotification(@AuthenticationPrincipal User user) {

        String messages = notificationService.sendNotification(user);
        return ResponseEntity.ok(messages);
    }
}