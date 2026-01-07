package com.project.learning.Repository;

import com.project.learning.Dto.Response.CourseResponse;
import com.project.learning.Dto.Response.EnrollmentResponse;
import com.project.learning.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {

    List<CourseEntity> findByPublishedTrue();

    @Query("""
            
            SELECT new com.project.learning.Dto.Response.EnrollmentResponse(
            c.id,
            c.title,
            c.price,
            COUNT(e.id),
            c.published
            )
            FROM CourseEntity c
            LEFT JOIN EnrollmentEntity e ON e.course.id=c.id
            GROUP BY c.id
            """)
    List<EnrollmentResponse> getCourseEnrollments();
}
