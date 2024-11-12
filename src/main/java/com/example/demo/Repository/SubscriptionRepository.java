package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.Enum.SubscriptionEnum;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findByUserAndStatus(User user, SubscriptionEnum status);
    List<Subscription> findByCourseId(Integer courseId);
	Subscription findByCourseIdAndUserId(Integer courseId, Integer studentId);




}
