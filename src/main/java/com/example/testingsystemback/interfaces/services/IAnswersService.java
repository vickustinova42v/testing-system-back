package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.AnswersEntity;

import java.util.List;

public interface IAnswersService {
    AnswersEntity createAnswer(String name, Boolean isRightAnswer, Long questionId);
    List<AnswersEntity> getAnswersByQuestion(Long questionId);
    void delete(Long id);
}
