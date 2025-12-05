package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByName(String name);
}
