package com.project.learning.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CourseRequest {

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Course description is required")
    private String description;
    @NotNull(message = "Course duration is required")
    private Integer duration;
    @PositiveOrZero(message = "Price should not be <0")
    private double price;
    private boolean published;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPublished() {
        return published;
    }

    public Integer getDuration() {
        return duration;
    }
}
