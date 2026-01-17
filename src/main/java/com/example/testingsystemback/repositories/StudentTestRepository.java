package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.StudentTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTestRepository extends JpaRepository<StudentTestEntity, Long> {

    Optional<StudentTestEntity> findByStudent_IdAndTest_Id(Long studentId, Long testId);

    List<StudentTestEntity> findAllByStudent_Id(Long studentId);
}
