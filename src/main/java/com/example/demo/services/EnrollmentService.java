package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.Enrollment;
import com.example.demo.models.User;

public interface EnrollmentService {

    Enrollment enrollInCourse(User user, Course course);
    void approveEnrollment(Integer enrollmentId);

}
