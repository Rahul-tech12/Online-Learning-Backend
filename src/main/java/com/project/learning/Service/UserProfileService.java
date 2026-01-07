package com.project.learning.Service;

import com.project.learning.Dto.UserProfile;
import com.project.learning.Entity.UserEntity;
import com.project.learning.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

public UserRepository userRepository;

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getAuthenticatedUser(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username)
                .orElseThrow(()->new RuntimeException("User not found"));

    }

    public String updateUserProfile(UserProfile userProfile) {
        UserEntity user=getAuthenticatedUser();
        user.setUsername(userProfile.getUsername());
        user.setBio(userProfile.getBio());
        user.setProfileImage(userProfile.getProfileImage());
        userRepository.save(user);
        return "User profile updated successfully";
    }

    public UserProfile getUserProfile() {
        UserEntity user=getAuthenticatedUser();
        return new UserProfile(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getProfileImage()
        );
    }
}
