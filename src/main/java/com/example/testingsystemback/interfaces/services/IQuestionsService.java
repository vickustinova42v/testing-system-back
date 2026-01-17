package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.QuestionsEntity;

import java.util.List;

public interface IQuestionsService {
    QuestionsEntity createQuestion(String type, String name, Long subjectId);
    QuestionsEntity updateQuestion(Long id, String type, String name, Long subjectId);
    QuestionsEntity getQuestionById(Long id);
    List<QuestionsEntity> getQuestionsBySubject(Long subjectId);
    void delete(Long id);
}
