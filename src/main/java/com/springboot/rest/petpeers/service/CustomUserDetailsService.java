package com.springboot.rest.petpeers.service;

import com.springboot.rest.petpeers.entity.UserEntity;
import com.springboot.rest.petpeers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            // Create a default user if none found
            user = new UserEntity();
            user.setUsername("nisha");
            String plainPassword = "nisha";

            // Validate plain-text password length before hashing
            if (plainPassword.length() < 5 || plainPassword.length() > 15) {
                throw new IllegalArgumentException("Password must be between 5 and 15 characters");
            }

            // Encode the plain password before saving
            user.setPassword(passwordEncoder.encode(plainPassword));
            userRepository.save(user);
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER") // Adjust roles if needed
                .build();
    }
}
