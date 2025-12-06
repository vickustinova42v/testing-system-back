package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByEmail(String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.role.id = :roleId")
    List<UsersEntity> findAllByRoleId(Long roleId);

    @Query("SELECT u FROM UsersEntity u JOIN u.subjects s WHERE s.id = :subjectId")
    List<UsersEntity> findStudentsBySubjectId(Long subjectId);

    @Query("SELECT s FROM UsersEntity u JOIN u.subjects s WHERE u.id = :userId")
    List<Object> findSubjectsByStudentId(Long userId);

    @Query("SELECT u FROM UsersEntity u JOIN FETCH u.role WHERE u.id = :id")
    Optional<UsersEntity> findByIdWithRole(Long id);
}
