package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.SubjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepository extends JpaRepository<SubjectsEntity, Long> {

    @Query("SELECT s FROM SubjectsEntity s WHERE s.teacher.id = :teacherId")
    List<SubjectsEntity> findAllByTeacherId(Long teacherId);

    @Query("SELECT s FROM SubjectsEntity s JOIN s.students st WHERE st.id = :studentId")
    List<SubjectsEntity> findAllByStudentId(Long studentId);

    @Query("SELECT s FROM SubjectsEntity s LEFT JOIN FETCH s.students WHERE s.id = :id")
    SubjectsEntity findByIdWithStudents(Long id);

    @Query("SELECT s FROM SubjectsEntity s JOIN FETCH s.teacher WHERE s.id = :id")
    SubjectsEntity findByIdWithTeacher(Long id);

    @Query("""
           SELECT s FROM SubjectsEntity s
           LEFT JOIN FETCH s.teacher
           LEFT JOIN FETCH s.students
           WHERE s.id = :id
           """)
    SubjectsEntity findFullById(Long id);
}
