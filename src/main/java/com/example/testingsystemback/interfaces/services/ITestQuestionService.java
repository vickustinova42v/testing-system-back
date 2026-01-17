package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.TestQuestionEntity;

import java.util.List;

public interface ITestQuestionService {
    TestQuestionEntity addQuestionToTest(Long testId, Long questionId);
    List<TestQuestionEntity> getQuestionsByTest(Long testId);
    void delete(Long id);
}
