package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.RolesEntity;
import com.example.testingsystemback.services.RolesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public List<RolesEntity> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RolesEntity getRoleById(@PathVariable Long id) {
        return rolesService.getRoleById(id);
    }
}
