package com.project.learning.Service;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Entity.CourseEntity;
import com.project.learning.Repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    public CourseRepository courseRepository;

    public UserService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly=true)
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
