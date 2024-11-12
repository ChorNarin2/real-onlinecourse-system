package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
