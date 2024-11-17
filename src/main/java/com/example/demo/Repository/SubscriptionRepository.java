package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.Enum.SubscriptionEnum;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findByUserAndStatus(User user, SubscriptionEnum status);
    List<Subscription> findByCourseId(Integer courseId);
    List<Subscription> findByUserId(Integer Id);
	Subscription findByCourseIdAndUserId(Integer courseId, Integer studentId);
    Subscription findByIdAndUserId(Integer id, Integer userId);


    




}
