package com.example.demo.services;

import com.example.demo.dto.Course.CourseDto;
import com.example.demo.dto.Course.CourseUpdateDto;
import com.example.demo.models.Course;
import com.example.demo.models.User;

import java.util.List;


public interface CourseService {
    // Course create(CourseDto courseDto);
    Course findById(Integer Id);
    List<Course> findByTitle(String title);
    List<Course> findAll();
    boolean isUserCourseAdmin(User user, Integer courseId);

    Course createCourse(CourseDto courseDto, User user);
    Course updateCourse(CourseUpdateDto courseUpdateDto, User user, Integer courseId);
    void deleteCourse(User user, Integer courseId);




}
