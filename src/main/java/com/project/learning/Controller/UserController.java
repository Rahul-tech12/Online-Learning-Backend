package com.project.learning.Controller;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Service.CourseService;
import com.project.learning.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getCourses(){
        List<CourseResponse> courseResponses=userService.getPublishedCourses();
        return ResponseEntity.ok(courseResponses);
    }

}
