package com.project.learning.Dto.Response;

public class EnrollmentResponse {

    private Long course_id;
    private String title;
    private double price;
    private long totalEnrollments;
    private boolean published;

    public EnrollmentResponse(Long course_id, String title, double price, long totalEnrollments, boolean published) {
        this.course_id = course_id;
        this.title = title;
        this.price = price;
        this.totalEnrollments = totalEnrollments;
        this.published = published;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public long getTotalEnrollments() {
        return totalEnrollments;
    }

    public boolean isPublished() {
        return published;
    }
}
