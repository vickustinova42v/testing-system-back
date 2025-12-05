package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
