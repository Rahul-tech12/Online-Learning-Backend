package com.project.learning.Security;


import com.project.learning.Service.AuthService;
import com.project.learning.Service.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private com.project.learning.Service.UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            filterChain.doFilter(request,response);
            return;
        }
        String header=request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")){
            String token=header.substring(7);
            if(jwtUtil.isTokenValid(token)){
                String email=jwtUtil.extractEmail(token);
                UserDetails userDetails=userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authToken=new
                        UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request,response);
    }

}
