/*
package com.javapulses.springsecurity;

import com.javapulses.springsecurity.model.User;
import com.javapulses.springsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.findByUsername("admin@javapulses.com").isPresent()) {
                User admin = new User();
                admin.setUsername("admin@javapulses.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(new HashSet<>(Arrays.asList("ADMIN", "USER")));
                userRepository.save(admin);
            }

            if (!userRepository.findByUsername("user@javapulses.com").isPresent()) {
                User user = new User();
                user.setUsername("user@javapulses.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(new HashSet<>(Collections.singletonList("USER")));
                userRepository.save(user);
            }
        };
    }
}
*/
