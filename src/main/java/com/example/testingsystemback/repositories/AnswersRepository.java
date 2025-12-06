package com.example.testingsystemback.repositories;

import com.example.testingsystemback.enteties.AnswersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<AnswersEntity, Long> {
    List<AnswersEntity> findAllByQuestionId(Long questionId);
    List<AnswersEntity> findByQuestionIdAndIsRightAnswer(Long questionId, boolean isRightAnswer);
}
