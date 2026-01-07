package com.project.learning.Controller;

import com.project.learning.Dto.Request.LoginRequest;
import com.project.learning.Dto.Request.RegisterRequest;
import com.project.learning.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        String msg=authService.register(request);
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody LoginRequest request){
        String msg=authService.login(request);
        return ResponseEntity.ok(msg);
    }
}
