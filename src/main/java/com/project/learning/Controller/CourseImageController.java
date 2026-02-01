package com.project.learning.Controller;

import com.project.learning.Entity.CourseImage;
import com.project.learning.Repository.CourseImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseImageController {

    public CourseImageRepository courseImageRepository;

    public CourseImageController(CourseImageRepository courseImageRepository) {
        this.courseImageRepository = courseImageRepository;
    }

    @GetMapping("/courses/{id}/image")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getImage(@PathVariable Long id){
        CourseImage image=courseImageRepository.findByCourse_Id(id)
                .orElseThrow(()->new RuntimeException("Image does not exist"));
        return ResponseEntity.ok()
                .header("Content-Type",image.getContentType())
                .body(image.getData());
    }
}
