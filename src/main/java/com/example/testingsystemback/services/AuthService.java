package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    public AuthService(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
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

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found after login"));
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();

        SecurityContextHolder.clearContext();
    }
}
