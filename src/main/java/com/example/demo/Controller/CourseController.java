package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Course.CourseDto;
import com.example.demo.models.Course;
import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.Enum.SubscriptionEnum;
import com.example.demo.models.Enum.VideoEnumStatus;
import com.example.demo.services.CourseService;
import com.example.demo.services.SubscriptionService;
import com.example.demo.dto.Course.CourseUpdateDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
// @PreAuthorize("hasRole('ADMIN')")
public class CourseController {
    private final CourseService courseService;
    private final SubscriptionService subscriptionService;

 @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CourseDto courseDto,
                                               @AuthenticationPrincipal User authenticatedUser) {
        Course createdCourse = courseService.createCourse(courseDto, authenticatedUser);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
    
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<?> getbyId(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping("/getbytitle/{title}")
    public ResponseEntity<?> getbytitle(@PathVariable String title){
        return ResponseEntity.ok(courseService.findByTitle(title));
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseUpdateDto courseUpdateDto, @AuthenticationPrincipal User user, @PathVariable Integer courseId){
        Course course = courseService.updateCourse(courseUpdateDto, user, courseId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteCourseById(@AuthenticationPrincipal User user, @PathVariable Integer courseId){
        courseService.deleteCourse(user, courseId);
        return ResponseEntity.ok("The Course IS DELETED");
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getallCourse(){
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{courseId}/enrollment")
    public ResponseEntity<?> getCourseEnrollment(@AuthenticationPrincipal User user, @PathVariable Integer courseId){
        // check the current user is amdin of the course
        if(!courseService.isUserCourseAdmin(user, courseId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not the onwer of the course");
        }
        // Fetchs all subscriptions/Enrollmant of the course
        else{
            List<Subscription> subscriptions = subscriptionService.getEnrollmentsByCourseId(courseId);
            return ResponseEntity.ok(subscriptions);
        }
    }

    @GetMapping("/{courseId}/enroll/{studentId}/approve")
    public ResponseEntity<?> ApproveEnrollment(@PathVariable Integer courseId, @PathVariable Integer studentId, @AuthenticationPrincipal User user){

        //check the current is the course creator
        if(!courseService.isUserCourseAdmin(user, courseId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not the onwer of the course");
        }

        // approve the enrollment
        boolean approved = subscriptionService.updateEnrollmentStatus(courseId, studentId,SubscriptionEnum.ACTIVE);
        if(approved){
            return ResponseEntity.ok("Enrollment approved and the student can now access the course.");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to approve the enrollment.");
        }

    }

    @GetMapping("/{courseId}/enroll/{studentId}/reject")
    public ResponseEntity<?> rejectEnrollment(@PathVariable Integer courseId, @PathVariable Integer studentId, @AuthenticationPrincipal User user) {

        // Check if the current user is the course creator (admin of the course)
        if (!courseService.isUserCourseAdmin(user, courseId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not the owner of this course");
        }

        // Reject the enrollment
        boolean isRejected = subscriptionService.updateEnrollmentStatus(courseId, studentId, SubscriptionEnum.REJECTED
        );

        if (isRejected) {
            return ResponseEntity.ok("Enrollment rejected.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enrollment rejection failed.");
        }
    }

}
