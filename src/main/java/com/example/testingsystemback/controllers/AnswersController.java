package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.AnswersEntity;
import com.example.testingsystemback.interfaces.services.IAnswersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswersController {

    private final IAnswersService answersService;

    public AnswersController(IAnswersService answersService) {
        this.answersService = answersService;
    }

    @PostMapping
    public AnswersEntity create(@RequestParam String name,
                                @RequestParam boolean isRight,
                                @RequestParam Long questionId) {
        return answersService.createAnswer(name, isRight, questionId);
    }

    @GetMapping("/question/{id}")
    public List<AnswersEntity> getByQuestion(@PathVariable Long id) {
        return answersService.getAnswersByQuestion(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        answersService.delete(id);
    }
}
