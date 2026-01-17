package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.StudentTestEntity;

import java.util.List;
import java.util.Map;

public interface IStudentTestService {
    StudentTestEntity submitTest(Long studentId, Long testId, Map<Long, List<Long>> studentAnswers);
}
