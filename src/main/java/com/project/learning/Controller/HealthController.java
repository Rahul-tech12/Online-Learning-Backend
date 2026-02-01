package com.project.learning.Controller;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Service.CourseService;
import com.project.learning.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthController {
    @GetMapping("/")
    public String health() {
        return "Backend is running!";
    }
}
