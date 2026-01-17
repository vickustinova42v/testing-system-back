package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.UsersEntity;

public interface IAuthService {
    UsersEntity register(String lastName, String firstName, String fathersName, String email, String password, Long roleId);
    String login(String email, String password);
}
