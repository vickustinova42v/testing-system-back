package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.RolesEntity;

import java.util.List;

public interface IRolesService {
    RolesEntity getRoleById(Long id);
    RolesEntity getRoleByName(String name);
    List<RolesEntity> getAllRoles();
}
