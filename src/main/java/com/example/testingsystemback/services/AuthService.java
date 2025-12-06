package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsersRepository usersRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UsersEntity register(UsersEntity user) {

        if (usersRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public UsersEntity login(HttpServletRequest request, String email, String password) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        request.getSession(true); // создаёт сессию

        return usersRepository.findByEmail(email).get();
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
    }
}

