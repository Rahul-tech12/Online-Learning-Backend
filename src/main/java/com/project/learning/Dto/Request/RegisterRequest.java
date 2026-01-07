package com.project.learning.Dto.Request;

import com.project.learning.Enums.Role;
import com.project.learning.validation.PasswordMatch;
import jakarta.validation.constraints.*;

@PasswordMatch
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    private String email;
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).{8,}$",
            message = "Password must contain letters,numbers and symbols"
    )
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
