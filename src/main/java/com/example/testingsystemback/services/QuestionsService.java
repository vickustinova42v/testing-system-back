package com.example.testingsystemback.services;

import com.example.testingsystemback.interfaces.services.IQuestionsService;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.repositories.QuestionsRepository;
import com.example.testingsystemback.repositories.SubjectsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService implements IQuestionsService {

    private final QuestionsRepository questionsRepository;
    private final SubjectsRepository subjectsRepository;

    public QuestionsService(QuestionsRepository questionsRepository,
                            SubjectsRepository subjectsRepository) {
        this.questionsRepository = questionsRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public QuestionsEntity createQuestion(String type, String name, Long subjectId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        QuestionsEntity q = new QuestionsEntity();
        type = (type == null ? "" : type.trim().toLowerCase());
        if (!type.equals("single") && !type.equals("multi")) {
            throw new IllegalArgumentException("Тип вопроса должен быть single или multi");
        }

        q.setType(type);
        q.setName(name);
        q.setSubject(subject);

        return questionsRepository.save(q);
    }

    public List<QuestionsEntity> getQuestionsBySubject(Long subjectId) {
        return questionsRepository.findAllBySubjectId(subjectId);
    }

    public void delete(Long id) {
        questionsRepository.deleteById(id);
    }
}
