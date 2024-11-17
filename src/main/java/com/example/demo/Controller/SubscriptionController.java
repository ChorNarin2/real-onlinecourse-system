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

import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.services.SubscriptionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Subscription> subscribe(@AuthenticationPrincipal User user, @RequestParam Integer courseId) {
        Subscription subscription = subscriptionService.subscribeUserToCourse(user, courseId);
        return ResponseEntity.ok(subscription);
    }

    @GetMapping("/subscribers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@AuthenticationPrincipal User user) {
        List<Subscription> subscriptions = subscriptionService.getActiveSubscriptions(user);
        if(subscriptions == null || subscriptions.isEmpty()){
            System.out.println("No active subscriptions found with Id"+ user.getId());
        }
        else{
            System.out.println("Active subscriptions found with Id"+ user.getId());
        }
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/cancel")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> cancelSubscription(@RequestParam Integer subscriptionId) {
        subscriptionService.unsubscribeUserFromCourse(subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
