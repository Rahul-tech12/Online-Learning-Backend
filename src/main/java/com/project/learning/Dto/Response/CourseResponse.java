package com.project.learning.Dto.Response;

public class CourseResponse {

    private Long course_id;

    private String title;
    private String description;
    private Integer duration;
    private double price;

    private String imageUrl;

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CourseResponse(Long course_id, String title, String description, Integer duration, double price, String imageUrl) {
        this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
