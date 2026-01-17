package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.interfaces.services.IQuestionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
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

    @PutMapping("/{id}")
    public QuestionsEntity update(
            @PathVariable Long id,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long subjectId
    ) {
        return questionsService.updateQuestion(id, type, name, subjectId);
    }

    @GetMapping("/{id}")
    public QuestionsEntity getById(@PathVariable Long id) {
        return questionsService.getQuestionById(id);
    }

    @GetMapping("/subject/{subjectId}")
    public List<QuestionsEntity> getBySubject(@PathVariable Long subjectId) {
        return questionsService.getQuestionsBySubject(subjectId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        questionsService.delete(id);
    }
}
