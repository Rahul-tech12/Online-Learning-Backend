package com.project.learning.Service;

import com.cloudinary.utils.ObjectUtils;
import com.project.learning.Dto.Request.CourseRequest;
import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Dto.Response.EnrollmentResponse;
import com.project.learning.Entity.CourseEntity;
import com.project.learning.Repository.CourseRepository;
import com.project.learning.Repository.EnrollmentRepository;
import com.project.learning.config.CloudinaryConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    public CourseRepository courseRepository;
    public EnrollmentRepository enrollmentRepository;
    public CloudinaryConfig cloudinaryConfig;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, CloudinaryConfig cloudinary) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.cloudinaryConfig = cloudinary;
    }

    public CourseResponse upload_course(CourseRequest requestData, MultipartFile imageFile) throws IOException {
        CourseEntity entity = new CourseEntity();
            entity.setTitle(requestData.getTitle());
            entity.setDescription(requestData.getDescription());
            entity.setDuration(requestData.getDuration());
            entity.setPrice(requestData.getPrice());
            Map result=cloudinaryConfig.cloudinary().uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
            entity.setImage(result.get("secure_url").toString());
            CourseEntity savedEntity = courseRepository.save(entity);


            return new CourseResponse(
                    savedEntity.getId(),
                    savedEntity.getTitle(),
                    savedEntity.getDescription(),
                    savedEntity.getDuration(),
                    savedEntity.getPrice(),
                    savedEntity.getImage()

            );

    }

    public List<CourseResponse> getAllCourses(){
        List<CourseEntity> courses=courseRepository.findAll();

        return courses.stream().map(course->{
            return new CourseResponse(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getDuration(),
                    course.getPrice(),
                    course.getImage()
            );
        }).toList();
    }

    public CourseResponse updateCourseById(Long course_id,CourseRequest requestData, MultipartFile imageFile) throws IOException{
        CourseEntity course=courseRepository.findById(course_id)
                .orElseThrow(()->new RuntimeException("Course with id"+course_id+ "does not exist"));
        course.setTitle(requestData.getTitle());
        course.setDescription(requestData.getDescription());
        course.setDuration(requestData.getDuration());
        course.setPrice(requestData.getPrice());


        CourseEntity savedEntity=courseRepository.save(course);
        return new CourseResponse(
                savedEntity.getId(),
                savedEntity.getTitle(),
                savedEntity.getDescription(),
                savedEntity.getDuration(),
                savedEntity.getPrice(),
                savedEntity.getImage()
        );
    }

    @Transactional
    public String deleteCourseById(Long course_id){
        enrollmentRepository.deleteByCourse_Id(course_id);
        CourseEntity entity=courseRepository.findById(course_id)
                .orElseThrow(()->new RuntimeException("Course with id"+course_id+"does not exist"));
        courseRepository.delete(entity);
        return "Course with id " + course_id+ " deleted successfully";
    }

    public List<EnrollmentResponse> getCoursesWithEnrollment(){
        return courseRepository.getCourseEnrollments();
    }
}
