package com.project.learning.Controller;

import com.project.learning.Entity.UserEntity;
import com.project.learning.Enums.Role;
import com.project.learning.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/grant-instructor/{id}")
    public ResponseEntity<?> grantInstructor(@PathVariable Long id){
        UserEntity entity=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));
        entity.setRole(Role.ROLE_INSTRUCTOR);
        userRepository.save(entity);
        return ResponseEntity.ok("Instructor role granted!");
    }


}
