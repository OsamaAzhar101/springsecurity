package com.javapulses.springsecurity.controller;

import com.javapulses.springsecurity.dto.AuthenticationRequest;
import com.javapulses.springsecurity.dto.AuthenticationResponse;
import com.javapulses.springsecurity.interceptor.RoleInterceptor;
import com.javapulses.springsecurity.service.JwtUtil;
import com.javapulses.springsecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RoleInterceptor(role = "ADMIN")
    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin access granted!";
    }

    @RoleInterceptor(role = "USER")
    @GetMapping("/user")
    public String userAccess() {
        return "User access granted!";
    }
}
