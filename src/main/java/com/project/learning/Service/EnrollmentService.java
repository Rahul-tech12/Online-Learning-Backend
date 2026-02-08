package com.project.learning.Service;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Entity.CourseEntity;
import com.project.learning.Entity.EnrollmentEntity;
import com.project.learning.Entity.UserEntity;
import com.project.learning.Repository.CourseRepository;
import com.project.learning.Repository.EnrollmentRepository;
import com.project.learning.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    public EnrollmentRepository enrollmentRepository;
    public CourseRepository courseRepository;
    public UserRepository userRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public String enroll(String email, Long courseId){
        UserEntity user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        CourseEntity course=courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("Course not found"));
        if(enrollmentRepository.existsByUser_IdAndCourse_Id(user.getId(),courseId)){
            throw new RuntimeException("User already enrolled this course");
        }
        EnrollmentEntity enrollmentEntity=new EnrollmentEntity();
        enrollmentEntity.setUser(user);
        enrollmentEntity.setCourse(course);
        enrollmentRepository.save(enrollmentEntity);
        return user.getUsername()+" enrolled in course "+course.getTitle()+"successfully";
    }

    @Transactional(readOnly=true)
    public List<CourseResponse> getEnrolledCourses(String email){
        UserEntity user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        return enrollmentRepository.findByUser_Id(user.getId())
                .stream()
                .map(e->{
                    CourseEntity c=e.getCourse();
                    return new CourseResponse(
                            c.getId(),
                            c.getTitle(),
                            c.getDescription(),
                            c.getDuration(),
                            c.getPrice(),
                            c.getImage()
                    );
                }).toList();
    }
}
