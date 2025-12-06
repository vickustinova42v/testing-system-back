package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.TestQuestionEntity;
import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.repositories.TestQuestionRepository;
import com.example.testingsystemback.repositories.TestsRepository;
import com.example.testingsystemback.repositories.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService {

    private final TestQuestionRepository testQuestionRepository;
    private final TestsRepository testsRepository;
    private final QuestionsRepository questionsRepository;

    public TestQuestionService(TestQuestionRepository testQuestionRepository,
                               TestsRepository testsRepository,
                               QuestionsRepository questionsRepository) {
        this.testQuestionRepository = testQuestionRepository;
        this.testsRepository = testsRepository;
        this.questionsRepository = questionsRepository;
    }

    public TestQuestionEntity addQuestionToTest(Long testId, Long questionId) {
        TestsEntity test = testsRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        QuestionsEntity question = questionsRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        TestQuestionEntity tq = new TestQuestionEntity();
        tq.setTest(test);
        tq.setQuestion(question);

        return testQuestionRepository.save(tq);
    }

    public List<QuestionsEntity> getQuestionsByTest(Long testId) {
        return testQuestionRepository.findQuestionsByTestId(testId);
    }
}
