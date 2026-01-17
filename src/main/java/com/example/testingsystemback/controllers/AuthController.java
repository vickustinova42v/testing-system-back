package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.interfaces.services.IAuthService;
import com.example.testingsystemback.interfaces.services.IUsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;
    private final IUsersService usersService;

    public AuthController(IAuthService authService, IUsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersEntity user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersEntity user, HttpServletRequest request) {
        UsersEntity u = authService.login(request, user.getEmail(), user.getPassword());
        return ResponseEntity.ok(Map.of(
                "id", u.getId(),
                "email", u.getEmail(),
                "role", u.getRole().getName(),
                "firstName", u.getFirstName(),
                "lastName", u.getLastName()
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        String email = auth.getName();
        UsersEntity user = usersService.getUserByEmail(email);

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "role", user.getRole().getName(),
                "firstName", user.getFirstName(),
                "lastName", user.getLastName()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Logged out");
    }
}
