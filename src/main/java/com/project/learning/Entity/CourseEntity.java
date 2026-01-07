package com.project.learning.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long id;

    private String title;
    private String description;
    private Integer duration;
    private double price;
    private boolean published=true;

    @OneToOne(mappedBy = "course", cascade=CascadeType.ALL)
    private CourseImage image;

    public CourseImage getImage() {
        return image;
    }

    public void setImage(CourseImage image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
