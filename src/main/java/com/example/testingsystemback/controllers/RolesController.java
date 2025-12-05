package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.RolesEntity;
import com.example.testingsystemback.services.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesEntity> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(rolesService.getRoleById(id));
    }

    @GetMapping
    public ResponseEntity<List<RolesEntity>> getAllRoles() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RolesEntity> getRoleByName(@PathVariable String name) {
        return ResponseEntity.ok(rolesService.getRoleByName(name));
    }
}
