package com.project.learning.Security;

import com.project.learning.Entity.UserEntity;
import com.project.learning.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public UserRepository userRepository;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String email){
        UserEntity user=userRepository.findByEmail(email)
                .orElseThrow();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
                    return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
