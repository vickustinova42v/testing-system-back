package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.UsersEntity;

import java.util.List;

public interface IUsersService {
    List<UsersEntity> getAllUsers();
    UsersEntity updateUserData(Long id, UsersEntity newData);
    UsersEntity changePassword(Long id, String oldPassword, String newPassword);
    UsersEntity getUserById(Long id);
    UsersEntity getUserByEmail(String email);
    void deleteUser(Long id);
}
