package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dto.UserDto;
import com.example.entities.User;
import com.example.repositories.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

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
        user.setPassword(userDto.getPassword()); // Password will be stored as plain text
        user.setRole("USER");
        
        userRepository.save(user);
    }

    public User login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}