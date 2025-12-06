package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEntity, Long> {
    List<QuestionsEntity> findAllBySubjectId(Long subjectId);

    @Query("SELECT q FROM QuestionsEntity q " +
            "JOIN TestQuestionEntity tq ON tq.question.id = q.id " +
            "WHERE tq.test.id = :testId")
    List<QuestionsEntity> findByTestId(Long testId);
}
