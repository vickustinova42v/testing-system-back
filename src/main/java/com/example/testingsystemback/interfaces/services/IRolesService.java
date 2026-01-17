package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.RolesEntity;

import java.util.List;

public interface IRolesService {
    RolesEntity createRole(String name);
    List<RolesEntity> getAllRoles();
    void delete(Long id);
}
