package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.enteties.TestQuestionEntity;
import com.example.testingsystemback.interfaces.services.ITestQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestQuestionsController {

    private final ITestQuestionService testQuestionService;

    public TestQuestionsController(ITestQuestionService testQuestionService) {
        this.testQuestionService = testQuestionService;
    }

    @PostMapping("/{testId}/questions/{questionId}")
    public TestQuestionEntity addQuestion(
            @PathVariable Long testId,
            @PathVariable Long questionId
    ) {
        return testQuestionService.addQuestionToTest(testId, questionId);
    }

    @GetMapping("/{testId}/questions")
    public List<QuestionsEntity> getQuestions(@PathVariable Long testId) {
        return testQuestionService.getQuestionsByTest(testId);
    }
}
