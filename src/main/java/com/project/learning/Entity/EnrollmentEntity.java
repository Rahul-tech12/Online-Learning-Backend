package com.project.learning.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="enrolled",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","course_id"})
})
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable=false)
    public UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id" ,nullable=false)
    public  CourseEntity course;

    public LocalDateTime enrolledAt=LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
