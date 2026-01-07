package com.project.learning.Service;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Entity.CourseEntity;
import com.project.learning.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public CourseRepository courseRepository;

    public UserService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseResponse> getPublishedCourses(){
        List<CourseEntity> courses=courseRepository.findByPublishedTrue();
        return courses.stream().map(course->{
            String imageUrl=course.getImage()!=null
                    ?"/courses/"+course.getId()+"/image":null;

            return new CourseResponse(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getDuration(),
                    course.getPrice(),
                    imageUrl
            );
        }).toList();
    }
}
