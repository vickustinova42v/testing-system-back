package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.RolesEntity;
import com.example.testingsystemback.repositories.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public RolesEntity getRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
    }

    public RolesEntity getRoleByName(String name) {
        return rolesRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
    }

    public java.util.List<RolesEntity> getAllRoles() {
        return rolesRepository.findAll();
    }
}