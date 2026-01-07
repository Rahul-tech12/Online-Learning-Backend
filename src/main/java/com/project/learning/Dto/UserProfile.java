package com.project.learning.Dto;

import jakarta.validation.constraints.Email;

public class UserProfile {

    private Long id;
    private String username;
    private String email;
    private String bio;
    private String profileImage;

    public UserProfile(Long id, String username, String email, String bio, String profileImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
