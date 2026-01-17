package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.RolesEntity;
import com.example.testingsystemback.interfaces.services.IRolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    private final IRolesService rolesService;

    public RolesController(IRolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<RolesEntity>> getAllRoles() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }
}
