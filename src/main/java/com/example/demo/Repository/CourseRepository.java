package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Course;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Optional<Course> findById(Integer id);
    @Query("SELECT c from Course c where c.title LIKE %:title%")
    List<Course> findByTitleContaining(@Param("title") String title);

    
    
    

}
