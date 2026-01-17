package com.example.testingsystemback.services;

import com.example.testingsystemback.interfaces.services.IAnswersService;

import com.example.testingsystemback.enteties.AnswersEntity;
import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.repositories.AnswersRepository;
import com.example.testingsystemback.repositories.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersService implements IAnswersService {

    private final AnswersRepository answersRepository;
    private final QuestionsRepository questionsRepository;

    public AnswersService(AnswersRepository answersRepository,
                          QuestionsRepository questionsRepository) {
        this.answersRepository = answersRepository;
        this.questionsRepository = questionsRepository;
    }

    @Override
    public AnswersEntity createAnswer(String name, boolean isRightAnswer, Long questionId) {
        QuestionsEntity question = questionsRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        AnswersEntity ans = new AnswersEntity();
        ans.setName(name);
        ans.setIsRightAnswer(isRightAnswer);
        ans.setQuestion(question);

        return answersRepository.save(ans);
    }

    @Override
    public List<AnswersEntity> getAnswersByQuestion(Long questionId) {
        return answersRepository.findAllByQuestionId(questionId);
    }

    @Override
    public void delete(Long id) {
        answersRepository.deleteById(id);
    }
}
