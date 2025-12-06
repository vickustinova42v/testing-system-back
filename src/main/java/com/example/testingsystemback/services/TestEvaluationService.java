package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.AnswersEntity;
import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.repositories.AnswersRepository;
import com.example.testingsystemback.repositories.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestEvaluationService {

    private final QuestionsRepository questionsRepository;
    private final AnswersRepository answersRepository;

    public TestEvaluationService(
            QuestionsRepository questionsRepository,
            AnswersRepository answersRepository
    ) {
        this.questionsRepository = questionsRepository;
        this.answersRepository = answersRepository;
    }

    /**
     * @param testId ID теста
     * @param studentAnswers Map<questionId, List<answerId>>
     * @return итоговая оценка
     */
    public int evaluateTest(Long testId, Map<Long, List<Long>> studentAnswers) {

        // получаем все вопросы теста
        List<QuestionsEntity> testQuestions =
                questionsRepository.findByTestId(testId);

        int correct = 0;

        for (QuestionsEntity q : testQuestions) {
            List<AnswersEntity> correctAnswers =
                    answersRepository.findByQuestionIdAndIsRightAnswer(q.getId(), true);

            List<Long> correctIds = correctAnswers.stream()
                    .map(AnswersEntity::getId)
                    .toList();

            List<Long> chosenIds = studentAnswers.get(q.getId());

            if (chosenIds == null || chosenIds.isEmpty())
                continue;

            if (correctIds.size() == chosenIds.size()
                    && correctIds.containsAll(chosenIds)) {
                correct++;
            }
        }

        // оценка в процентах
        return (int) (((double) correct / testQuestions.size()) * 100);
    }
}
