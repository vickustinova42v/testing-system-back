package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Получить всех пользователей (админ)
     */
    @GetMapping
    public ResponseEntity<List<UsersEntity>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersEntity> updateUser(
            @PathVariable Long id,
            @RequestBody UsersEntity userData
    ) {
        UsersEntity updated = usersService.updateUserData(id, userData);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        usersService.changePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok("Пароль успешно изменён");
    }
}
