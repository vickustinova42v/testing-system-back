package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.RolesEntity;
import com.example.testingsystemback.repositories.RolesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<RolesEntity> getAllRoles() {
        return rolesRepository.findAll();
    }

    public RolesEntity getRoleById(Long id) {
        return rolesRepository.findById(id)
                .orElse(null); // можно кинуть ошибку, если хочешь
    }
}
