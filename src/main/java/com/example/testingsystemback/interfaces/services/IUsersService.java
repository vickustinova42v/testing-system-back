package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.UsersEntity;

import java.util.List;

public interface IUsersService {
    UsersEntity createUser(String lastName, String firstName, String fathersName, String email, String password, Long roleId);
    List<UsersEntity> getAllUsers();
    UsersEntity getUserById(Long id);
    void delete(Long id);
}
