package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectStudentRepository extends JpaRepository<SubjectStudentEntity, Long> {

    List<SubjectStudentEntity> findBySubject(SubjectsEntity subject);

    Optional<SubjectStudentEntity> findBySubjectAndStudent(SubjectsEntity subject, UsersEntity student);
}
