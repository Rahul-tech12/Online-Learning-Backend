package com.project.learning.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotBlank(message="Email is required")
    private String email;
    @NotBlank(message="Password is required")
    @Size(min=8,message="Password must be of atleast 8 characters")
    private String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
