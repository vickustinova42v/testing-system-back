package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.QuestionsEntity;

import java.util.List;

public interface IQuestionsService {
    QuestionsEntity createQuestion(String type, String name, Long subjectId);
    List<QuestionsEntity> getQuestionsBySubject(Long subjectId);
    void delete(Long id);
}
