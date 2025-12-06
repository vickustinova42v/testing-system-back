package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.TestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestsRepository extends JpaRepository<TestsEntity, Long> {
    List<TestsEntity> findAllBySubjectId(Long subjectId);
}
