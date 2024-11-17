package com.example.demo.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.models.Course;
import com.example.demo.models.Notification;
import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.Enum.SubscriptionEnum;
import com.example.demo.services.NotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final SubscriptionRepository subscriptionRepository;

    // create new notification for user
    @Override
    public void createNotification(User user, String messages) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(messages);
        notificationRepository.save(notification);
    }

    // fetch all unread notifications for a user
    @Override
    public List<Notification> getUnreadNotifications(Integer userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    // Mark a notification as read
    @Override
    public void MarkAsRead(Integer NotificationId) {
        Notification notification = notificationRepository.findById(NotificationId)
        .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }

    // send notification to user when a new subscription is made
    // Student gets the notifications
    @Override
    public String sendNotification(User user){
       // Find all subscriptions where the user is registered
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(user.getId());

        if(subscriptions == null || subscriptions.isEmpty()){
            throw new IllegalStateException("No subscriptions found for this user.");
        }

        StringBuilder message = new StringBuilder();
        boolean hasActiveSubscription = false;

        for (Subscription subscription : subscriptions) {
            // Check if the subscription is active
            if (subscription.getStatus().equals(SubscriptionEnum.ACTIVE)) {
                Course course = subscription.getCourse();
    
                if (course != null) {
                    message.append("You have successfully subscribed to the course: ").append(course.getTitle()).append("\n");
                    hasActiveSubscription = true;
                }
            }
        }
    
        if (!hasActiveSubscription) {
            throw new IllegalStateException("No active subscriptions found.");
        }
    
        return message.toString();  // Return the notification message for all active subscriptions


        
    }
}
