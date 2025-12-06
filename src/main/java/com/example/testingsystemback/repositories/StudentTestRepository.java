package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.StudentTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTestRepository extends JpaRepository<StudentTestEntity, Long> {
}
