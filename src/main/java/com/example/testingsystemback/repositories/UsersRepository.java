package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByEmail(String email);

    // Получить пользователей по ID роли
    @Query("SELECT u FROM UsersEntity u WHERE u.role.id = :roleId")
    List<UsersEntity> findAllByRoleId(Long roleId);

    // Получить студентов предмета
    @Query("""
        SELECT u 
        FROM UsersEntity u
        JOIN u.subjectsAsStudent ss
        WHERE ss.subject.id = :subjectId
    """)
    List<UsersEntity> findStudentsBySubjectId(Long subjectId);

    // Получить предметы, где студент участвует
    @Query("""
        SELECT ss.subject
        FROM UsersEntity u
        JOIN u.subjectsAsStudent ss
        WHERE u.id = :userId
    """)
    List<SubjectsEntity> findSubjectsByStudentId(Long userId);

    // Получить преподавателя предмета
    @Query("""
        SELECT s
        FROM SubjectsEntity s
        WHERE s.teacher.id = :teacherId
    """)
    List<SubjectsEntity> findSubjectsByTeacherId(Long teacherId);

    // Получение пользователя с ролью
    @Query("SELECT u FROM UsersEntity u JOIN FETCH u.role WHERE u.id = :id")
    Optional<UsersEntity> findByIdWithRole(Long id);
}
