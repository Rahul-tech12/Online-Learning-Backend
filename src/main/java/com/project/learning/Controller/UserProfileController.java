package com.project.learning.Controller;

import com.project.learning.Dto.UserProfile;
import com.project.learning.Service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profile")
@PreAuthorize("hasRole('STUDENT')")
public class UserProfileController {

    public UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody UserProfile userProfile){
        String response=userProfileService.updateUserProfile(userProfile);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UserProfile> getProfile(){
        UserProfile response=userProfileService.getUserProfile();
        return ResponseEntity.ok(response);
    }


}
