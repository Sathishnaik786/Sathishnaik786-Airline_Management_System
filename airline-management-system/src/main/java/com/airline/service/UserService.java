package com.airline.service;

import com.airline.dto.request.RegisterRequest;
import com.airline.dto.response.AuthResponse;
import com.airline.model.User;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    User getCurrentUser();
    User getUserById(Long id);
    User getUserByUsername(String username);
    void deleteUser(Long id);
}