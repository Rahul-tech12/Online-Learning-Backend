package com.project.learning.Service;

import com.project.learning.Dto.Request.CourseRequest;
import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Dto.Response.EnrollmentResponse;
import com.project.learning.Entity.CourseEntity;
import com.project.learning.Entity.CourseImage;
import com.project.learning.Repository.CourseImageRepository;
import com.project.learning.Repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CourseService {

    public CourseRepository courseRepository;
    public CourseImageRepository courseImageRepository;

    public CourseService(CourseRepository courseRepository, CourseImageRepository courseImageRepository) {
        this.courseRepository = courseRepository;
        this.courseImageRepository = courseImageRepository;
    }

    public CourseResponse upload_course(CourseRequest requestData, MultipartFile imageFile) throws IOException {
        CourseEntity entity = new CourseEntity();
            entity.setTitle(requestData.getTitle());
            entity.setDescription(requestData.getDescription());
            entity.setDuration(requestData.getDuration());
            entity.setPrice(requestData.getPrice());

            CourseImage imageEntity = new CourseImage();
            imageEntity.setFileName(imageFile.getOriginalFilename());
            imageEntity.setContentType(imageFile.getContentType());
            imageEntity.setData(imageFile.getBytes());
            imageEntity.setSize(imageFile.getSize());

            imageEntity.setCourse(entity);
            entity.setImage(imageEntity);
            CourseEntity savedEntity = courseRepository.save(entity);
            CourseImage savedImage=courseImageRepository.save(imageEntity);

            String imageUrl = "/courses/" + savedEntity.getId() + "/image";

            return new CourseResponse(
                    savedEntity.getId(),
                    savedEntity.getTitle(),
                    savedEntity.getDescription(),
                    savedEntity.getDuration(),
                    savedEntity.getPrice(),
                    imageUrl

            );

    }

    public List<CourseResponse> getAllCourses(){
        List<CourseEntity> courses=courseRepository.findAll();

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

    public CourseResponse updateCourseById(Long course_id,CourseRequest requestData, MultipartFile imageFile) throws IOException{
        CourseEntity course=courseRepository.findById(course_id)
                .orElseThrow(()->new RuntimeException("Course with id"+course_id+ "does not exist"));
        course.setTitle(requestData.getTitle());
        course.setDescription(requestData.getDescription());
        course.setDuration(requestData.getDuration());
        course.setPrice(requestData.getPrice());

        if(imageFile!=null && !imageFile.isEmpty()) {
            CourseImage image = course.getImage();
            if (image == null) {
                image = new CourseImage();
                image.setCourse(course);
                course.setImage(image);
            }
            image.setFileName(imageFile.getOriginalFilename());
            image.setContentType(imageFile.getContentType());
            image.setData(imageFile.getBytes());
            image.setSize(imageFile.getSize());
        }
        CourseEntity savedEntity=courseRepository.save(course);
        String imageUrl=savedEntity.getImage()!=null?"/courses/"+savedEntity.getId()+"/image":null;
        return new CourseResponse(
                savedEntity.getId(),
                savedEntity.getTitle(),
                savedEntity.getDescription(),
                savedEntity.getDuration(),
                savedEntity.getPrice(),
                imageUrl
        );
    }

    public String deleteCourseById(Long course_id){
        CourseEntity entity=courseRepository.findById(course_id)
                .orElseThrow(()->new RuntimeException("Course with id"+course_id+"does not exist"));
        courseRepository.delete(entity);
        return "Course with id " + course_id+ " deleted successfully";
    }

    public List<EnrollmentResponse> getCoursesWithEnrollment(){
        return courseRepository.getCourseEnrollments();
    }
}
