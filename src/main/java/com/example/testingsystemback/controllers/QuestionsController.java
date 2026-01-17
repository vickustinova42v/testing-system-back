package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.interfaces.services.IQuestionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final IQuestionsService questionsService;

    public QuestionsController(IQuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @PostMapping
    public QuestionsEntity create(@RequestParam String type,
                                  @RequestParam String name,
                                  @RequestParam Long subjectId) {
        return questionsService.createQuestion(type, name, subjectId);
    }

    @GetMapping("/subject/{id}")
    public List<QuestionsEntity> getBySubject(@PathVariable Long id) {
        return questionsService.getQuestionsBySubject(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        questionsService.delete(id);
    }
}
