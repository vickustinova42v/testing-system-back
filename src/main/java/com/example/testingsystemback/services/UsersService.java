package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.RolesEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.UsersRepository;
import com.example.testingsystemback.repositories.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public UsersService(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersEntity updateUserData(Long id, UsersEntity newData) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        user.setFirstName(newData.getFirstName());
        user.setLastName(newData.getLastName());
        user.setFathersName(newData.getFathersName());

        if (newData.getRole() != null) {
            RolesEntity role = rolesRepository.findById(newData.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Роль не найдена"));
            user.setRole(role);
        }

        return usersRepository.save(user);
    }

    public UsersEntity changePassword(Long id, String oldPassword, String newPassword) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Старый пароль неверный");
        }

        user.setPassword(newPassword);
        return usersRepository.save(user);
    }

    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public String getUserRoleName(Long id) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return user.getRole().getName();
    }
}
