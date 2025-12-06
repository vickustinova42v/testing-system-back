package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.enteties.TestQuestionEntity;
import com.example.testingsystemback.services.TestQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-questions")
public class TestQuestionController {

    private final TestQuestionService testQuestionService;

    public TestQuestionController(TestQuestionService testQuestionService) {
        this.testQuestionService = testQuestionService;
    }

    @PostMapping
    public TestQuestionEntity addQuestion(@RequestParam Long testId,
                                          @RequestParam Long questionId) {
        return testQuestionService.addQuestionToTest(testId, questionId);
    }

    @GetMapping("/test/{id}")
    public List<QuestionsEntity> getQuestions(@PathVariable Long id) {
        return testQuestionService.getQuestionsByTest(id);
    }
}
