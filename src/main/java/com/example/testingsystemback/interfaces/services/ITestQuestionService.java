package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.enteties.TestQuestionEntity;

import java.util.List;

public interface ITestQuestionService {
    TestQuestionEntity addQuestionToTest(Long testId, Long questionId);
    List<QuestionsEntity> getQuestionsByTest(Long testId);
}
