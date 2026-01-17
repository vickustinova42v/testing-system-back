package com.example.testingsystemback.interfaces.services;

import java.util.List;
import java.util.Map;

public interface ITestEvaluationService {
    int evaluateTest(Long testId, Map<Long, List<Long>> studentAnswers);
}
