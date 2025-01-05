package com.javapulses.springsecurity.service;


import com.javapulses.springsecurity.model.User;
import com.javapulses.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Check if the password is encoded with BCrypt
        String password = user.getPassword();
        if (!password.startsWith("{bcrypt}")) {
            password = passwordEncoder.encode(password);
        }

        // Create and return a Spring Security User object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(password) // The password is expected to be hashed
                .authorities(Collections.emptyList()) // Add roles/authorities if needed
                .build();
    }
}

