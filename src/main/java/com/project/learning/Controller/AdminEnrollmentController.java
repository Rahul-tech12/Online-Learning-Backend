package com.project.learning.Controller;

import com.project.learning.Dto.Response.EnrollmentResponse;
import com.project.learning.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminEnrollmentController {

    public CourseService courseService;

    public AdminEnrollmentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/enrollments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EnrollmentResponse>> getCoursesWithEnrollments(){
        List<EnrollmentResponse> responseList=courseService.getCoursesWithEnrollment();
        return ResponseEntity.ok(responseList);
    }
}
