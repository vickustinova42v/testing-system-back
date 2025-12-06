package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.StudentTestEntity;
import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.StudentTestRepository;
import com.example.testingsystemback.repositories.TestsRepository;
import com.example.testingsystemback.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;
    private final UsersRepository usersRepository;
    private final TestsRepository testsRepository;
    private final TestEvaluationService testEvaluationService;

    public StudentTestService(
            StudentTestRepository studentTestRepository,
            UsersRepository usersRepository,
            TestsRepository testsRepository,
            TestEvaluationService testEvaluationService
    ) {
        this.studentTestRepository = studentTestRepository;
        this.usersRepository = usersRepository;
        this.testsRepository = testsRepository;
        this.testEvaluationService = testEvaluationService;
    }

    public StudentTestEntity submitTest(
            Long studentId,
            Long testId,
            Map<Long, List<Long>> studentAnswers
    ) {

        UsersEntity student = usersRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));

        TestsEntity test = testsRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Тест не найден"));

        int mark = testEvaluationService.evaluateTest(testId, studentAnswers);

        StudentTestEntity entity = new StudentTestEntity(mark, student, test);

        return studentTestRepository.save(entity);
    }
}
