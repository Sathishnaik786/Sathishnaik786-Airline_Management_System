package com.airline.service.impl;

import com.airline.dto.request.RegisterRequest;
import com.airline.dto.response.AuthResponse;
import com.airline.exception.CustomException;
import com.airline.model.Role;
import com.airline.model.User;
import com.airline.repository.UserRepository;
import com.airline.security.JwtService;
import com.airline.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException("Username is already taken", HttpStatus.BAD_REQUEST, "USERNAME_EXISTS");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException("Email is already in use", HttpStatus.BAD_REQUEST, "EMAIL_EXISTS");
        }
        
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.valueOf(request.getRole()))
                .build();
        
        User savedUser = userRepository.save(user);
        
        String jwtToken = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(savedUser.getUsername())
                        .password(savedUser.getPassword())
                        .roles(savedUser.getRole().name())
                        .build()
        );
        
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(
                        "User not found", HttpStatus.NOT_FOUND, "USER_NOT_FOUND"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }
}