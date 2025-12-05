package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        UsersEntity user = usersService.getUserById(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("firstName", user.getFirstName());
        result.put("lastName", user.getLastName());
        result.put("fathersName", user.getFathersName());
        result.put("email", user.getEmail());
        result.put("role", user.getRole().getName()); // ← СТРОКА, как ты просила

        return ResponseEntity.ok(result);
    }

}
