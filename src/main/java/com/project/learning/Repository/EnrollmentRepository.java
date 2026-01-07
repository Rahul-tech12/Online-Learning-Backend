package com.project.learning.Repository;

import com.project.learning.Dto.Response.EnrollmentResponse;
import com.project.learning.Entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> {

    boolean existsByUser_IdAndCourse_Id(Long userId, Long courseId);

    List<EnrollmentEntity> findByUser_Id(Long userId);
}
