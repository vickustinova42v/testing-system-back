package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.UsersEntity;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthService {
    UsersEntity register(UsersEntity user);
    UsersEntity login(HttpServletRequest request, String email, String password);
    void logout(HttpServletRequest request);
}
