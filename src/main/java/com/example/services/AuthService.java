package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.UserDto;
import com.example.entities.User;
import com.example.repositories.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(UserDto userDto) {
        if (userRepository.findByLoginId(userDto.getLoginId()) != null) {
            throw new RuntimeException("Login ID already exists");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setLoginId(userDto.getLoginId());
        user.setPassword(userDto.getPassword()); // Password should already be encoded
        user.setRole("ROLE_USER"); // Set default role
        
        userRepository.save(user);
    }

    public User login(String loginId, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginId, password)
            );
            
            if (authentication.isAuthenticated()) {
                return userRepository.findByLoginId(loginId);
            }
            return null;
        } catch (AuthenticationException e) {
            return null;
        }
    }
}