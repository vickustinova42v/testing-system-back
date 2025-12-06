package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.AnswersEntity;
import com.example.testingsystemback.services.AnswersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswersController {

    private final AnswersService answersService;

    public AnswersController(AnswersService answersService) {
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
