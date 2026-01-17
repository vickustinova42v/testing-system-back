package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.TestsEntity;

import java.util.List;

public interface ITestsService {
    TestsEntity createTest(Integer time, String name, Long subjectId);
    List<TestsEntity> getTestsBySubject(Long subjectId);
    void delete(Long id);
}
