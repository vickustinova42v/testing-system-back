package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.StudentTestEntity;
import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.interfaces.services.IStudentTestService;
import com.example.testingsystemback.interfaces.services.ITestEvaluationService;
import com.example.testingsystemback.repositories.StudentTestRepository;
import com.example.testingsystemback.repositories.TestsRepository;
import com.example.testingsystemback.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentTestService implements IStudentTestService {

    private final StudentTestRepository studentTestRepository;
    private final UsersRepository usersRepository;
    private final TestsRepository testsRepository;
    private final ITestEvaluationService testEvaluationService;

    public StudentTestService(
            StudentTestRepository studentTestRepository,
            UsersRepository usersRepository,
            TestsRepository testsRepository,
            ITestEvaluationService testEvaluationService
    ) {
        this.studentTestRepository = studentTestRepository;
        this.usersRepository = usersRepository;
        this.testsRepository = testsRepository;
        this.testEvaluationService = testEvaluationService;
    }

    @Override
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

        // из-за UNIQUE(student_id, test_id) нужно обновлять запись, если она уже есть
        StudentTestEntity entity = studentTestRepository
                .findByStudent_IdAndTest_Id(studentId, testId)
                .orElseGet(() -> new StudentTestEntity(null, student, test));

        entity.setMark(mark);
        entity.setStudent(student);
        entity.setTest(test);

        return studentTestRepository.save(entity);
    }
}
