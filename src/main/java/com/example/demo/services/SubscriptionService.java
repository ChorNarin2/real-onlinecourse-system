package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.Enum.SubscriptionEnum;
import com.example.demo.models.Enum.VideoEnumStatus;

public interface SubscriptionService {
    Subscription subscribeUserToCourse(User userId, Integer courseId);
    List<Subscription> getActiveSubscriptions(User user);
    void unsubscribeUserFromCourse(Integer SubscriptionId);
    List<Subscription> getEnrollmentsByCourseId(Integer courseId);
    boolean updateEnrollmentStatus(Integer courseId, Integer studentId, SubscriptionEnum status);

}
