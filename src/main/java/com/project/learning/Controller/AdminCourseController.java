package com.project.learning.Controller;

import com.project.learning.Dto.Request.CourseRequest;
import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Entity.CourseEntity;
import com.project.learning.Entity.CourseImage;
import com.project.learning.Repository.CourseImageRepository;
import com.project.learning.Service.CourseService;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/courses")
public class AdminCourseController {

    public CourseService courseService;
    public CourseImageRepository courseImageRepository;

    public AdminCourseController(CourseService courseService, CourseImageRepository courseImageRepository) {
        this.courseService = courseService;
        this.courseImageRepository = courseImageRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CourseResponse>> getCourses(){
        List<CourseResponse> courses=courseService.getAllCourses();
        return  ResponseEntity.ok(courses);
    }

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponse> addCourse(@RequestPart("data") CourseRequest courseRequest, @RequestPart(value="file", required = false) MultipartFile file) throws IOException {
        CourseResponse entity=courseService.upload_course(courseRequest,file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @PutMapping(value = "/{id}",consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id,@RequestPart("data") CourseRequest updateData,@RequestPart(value = "file",required = false) MultipartFile file) throws IOException{
        CourseResponse updatedCourse=courseService.updateCourseById(id,updateData,file);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) throws IOException{
        String response=courseService.deleteCourseById(id);
        return ResponseEntity.ok(response);
    }

}
