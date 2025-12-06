package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.TestQuestionEntity;
import com.example.testingsystemback.enteties.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestionEntity, Long> {

    List<TestQuestionEntity> findAllByTestId(Long testId);

    @Query("SELECT tq.question FROM TestQuestionEntity tq WHERE tq.test.id = :testId")
    List<QuestionsEntity> findQuestionsByTestId(Long testId);
}
