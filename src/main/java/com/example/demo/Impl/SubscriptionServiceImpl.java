package com.example.demo.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.Repository.VideoRepository;
import com.example.demo.models.Course;
import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.Enum.SubscriptionEnum;
import com.example.demo.models.Enum.VideoEnumStatus;
import com.example.demo.services.SubscriptionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private final CourseRepository courseRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final VideoRepository videoRepository;

    @Override
    public Subscription subscribeUserToCourse(User user, Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
            () -> new RuntimeException("Course not found"));
        
            Subscription subscription = new Subscription();
            subscription.setUser(user);
            subscription.setCourse(course);
            subscription.setStatus(SubscriptionEnum.PROGRESS);
            return subscriptionRepository.save(subscription);

    }

    @Override
    public List<Subscription> getActiveSubscriptions(User user) {
       return subscriptionRepository.findByUserAndStatus(user, SubscriptionEnum.ACTIVE);
    };

    @Override
    public void unsubscribeUserFromCourse(Integer SubscriptionId) {
       Subscription subscription = subscriptionRepository.findById(SubscriptionId)
       .orElseThrow(() -> new RuntimeException("Subscription not found"));

       subscription.setStatus(SubscriptionEnum.CANCELED);
       subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getEnrollmentsByCourseId(Integer courseId) {
        return subscriptionRepository.findByCourseId(courseId);
    }

    @Override
    public boolean updateEnrollmentStatus(Integer courseId, Integer studentId, SubscriptionEnum status) {   
        Subscription subscription = subscriptionRepository.findByCourseIdAndUserId(courseId, studentId);

        if(subscription != null && subscription.getStatus() == SubscriptionEnum.PROGRESS){
            subscription.setStatus(SubscriptionEnum.ACTIVE);
            subscriptionRepository.save(subscription);
            return true;
        }
        else {
            System.out.println("The status is not UNDER_PROGRESS, current status: " + SubscriptionEnum.PROGRESS);
            return false;
        }          
    }

}