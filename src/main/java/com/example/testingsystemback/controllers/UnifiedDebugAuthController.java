package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/debug")
public class UnifiedDebugAuthController {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UnifiedDebugAuthController(
            AuthenticationManager authenticationManager,
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth-check")
    public Map<String, Object> debugAuth(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String rawPassword = body.get("password");

        // 1. Проверяем пользователя в БД
        Optional<UsersEntity> userOpt = usersRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return Map.of(
                    "step", "DB lookup",
                    "ok", false,
                    "error", "UserNotFound",
                    "message", "Пользователь с таким email не найден"
            );
        }

        UsersEntity user = userOpt.get();

        // 2. Проверяем совпадение пароля
        boolean passwordMatches = passwordEncoder.matches(rawPassword, user.getPassword());

        // 3. Пробуем authenticate()
        try {
            var token = new UsernamePasswordAuthenticationToken(email, rawPassword);
            var auth = authenticationManager.authenticate(token);

            return Map.of(
                    "step", "authenticationManager.authenticate",
                    "ok", true,
                    "dbUserExists", true,
                    "passwordMatches", passwordMatches,
                    "principal", auth.getPrincipal().toString(),
                    "authorities", auth.getAuthorities()
            );

        } catch (AuthenticationException ex) {
            return Map.of(
                    "step", "authenticationManager.authenticate",
                    "ok", false,
                    "dbUserExists", true,
                    "passwordMatches", passwordMatches,
                    "error", ex.getClass().getSimpleName(),
                    "message", ex.getMessage()
            );
        }
    }
}
