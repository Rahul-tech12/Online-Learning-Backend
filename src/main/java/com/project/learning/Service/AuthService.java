package com.project.learning.Service;

import com.project.learning.Dto.Request.LoginRequest;
import com.project.learning.Dto.Request.RegisterRequest;
import com.project.learning.Entity.UserEntity;
import com.project.learning.Enums.Role;
import com.project.learning.Exception.ResouceNotFound;
import com.project.learning.Repository.UserRepository;
import com.project.learning.Security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository,JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil=jwtUtil;
    }

    public String register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User already exists. Login!");
        }
        UserEntity entity=new UserEntity();
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new RuntimeException("Password and confirm password must be same");
        }
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setRole(Role.STUDENT);
        UserEntity savedUser=userRepository.save(entity);
        return "Username: "+savedUser.getUsername()+" registered successfully";
    }

    public String login(LoginRequest request){
        UserEntity entity=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new ResouceNotFound("User is not registered. Register now!"));
        return jwtUtil.generateToken(request.getEmail());
    }

}
