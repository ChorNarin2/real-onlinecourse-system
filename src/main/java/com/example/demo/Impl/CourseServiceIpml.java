package com.example.demo.Impl;

import com.example.demo.Repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


import com.example.demo.Repository.CourseRepository;
import com.example.demo.dto.Course.CourseDto;
import com.example.demo.dto.Course.CourseUpdateDto;
import com.example.demo.mappers.CourseMapper;
import com.example.demo.models.Category;
import com.example.demo.models.Course;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.CourseService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceIpml implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private  CategoryRepository categoryRepository;



    @Override
    public List<Course> findByTitle(String title) {
        List<Course> findByName = courseRepository.findByTitleContaining(title);
        return findByName;
    }

    public Course createCourse(CourseDto courseDto, User user) {
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setUser(user);  // Associate the authenticated user with the course
        course.setCategory(new Category(courseDto.getCategory_id()));  


        // course = CourseMapper.INSTANCE.toCourse(courseDto, user);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(CourseUpdateDto courseUpdateDto, User user, Integer courseId) {
        // Fetch the course by its ID
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));     
    
        // Check if the authenticated user is the course owner
        if (!course.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to update this course");
        }
    
        // Update course fields with the data from courseUpdateDto
        course.setDescription(courseUpdateDto.getDescription());
        course.setPrice(courseUpdateDto.getPrice());
        course.setTitle(courseUpdateDto.getTitle());
    
        // Save the updated course
        return courseRepository.save(course);  // Save the modified course, not course2
    }
    

    @Override
    public void deleteCourse(User user, Integer courseId) {
        Course course = courseRepository.findById(courseId)
           .orElseThrow(() -> new RuntimeException("Course is not found"+ courseId));
        // check if the authenticated user is the course owner
        if(!course.getUser().getId().equals(user.getId())){
            throw new RuntimeException("you are not authorized to delete this course");
        }
        courseRepository.deleteById(courseId);
    }


    @Override
    public List<Course> findAll(){
        List<Course> findAllCourse = courseRepository.findAll();

        return findAllCourse;
    }

    @Override
    public boolean isUserCourseAdmin(User user, Integer courseId) {
        Course course = courseRepository.findById(courseId)
           .orElseThrow(() -> new RuntimeException("Course is not found"+ courseId));
        return course.getUser().getId().equals(user.getId());
    }

    @Override
    public Course findById(Integer Id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }  
}
