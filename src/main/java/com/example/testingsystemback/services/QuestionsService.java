package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.QuestionsEntity;
import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.repositories.QuestionsRepository;
import com.example.testingsystemback.repositories.SubjectsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

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
