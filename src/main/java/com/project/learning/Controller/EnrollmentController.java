package com.project.learning.Controller;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    public EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> enrollCourse(Authentication authentication, @PathVariable Long courseId){
        String response=enrollmentService.enroll(authentication.getName(),courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<CourseResponse>> getEnrolledCourses(Authentication authentication){
        List<CourseResponse> courses=enrollmentService.getEnrolledCourses(authentication.getName());
        return ResponseEntity.ok(courses);
    }
}
