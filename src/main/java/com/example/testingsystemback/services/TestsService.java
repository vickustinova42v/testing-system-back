package com.example.testingsystemback.services;

import com.example.testingsystemback.interfaces.services.ITestsService;

import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;
import com.example.testingsystemback.enteties.StudentTestEntity;
import com.example.testingsystemback.repositories.TestsRepository;
import com.example.testingsystemback.repositories.SubjectsRepository;
import com.example.testingsystemback.repositories.SubjectStudentRepository;
import com.example.testingsystemback.repositories.StudentTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService implements ITestsService {

    private final TestsRepository testsRepository;
    private final SubjectsRepository subjectsRepository;
    private final SubjectStudentRepository subjectStudentRepository;
    private final StudentTestRepository studentTestRepository;

    public TestsService(TestsRepository testsRepository,
                        SubjectsRepository subjectsRepository,
                        SubjectStudentRepository subjectStudentRepository,
                        StudentTestRepository studentTestRepository) {
        this.testsRepository = testsRepository;
        this.subjectsRepository = subjectsRepository;
        this.subjectStudentRepository = subjectStudentRepository;
        this.studentTestRepository = studentTestRepository;
    }

    @Override
    public TestsEntity createTest(Integer time, String name, Long subjectId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        TestsEntity test = new TestsEntity();
        test.setTime(time);
        test.setName(name);
        test.setSubject(subject);

        TestsEntity saved = testsRepository.save(test);

        List<SubjectStudentEntity> relations = subjectStudentRepository.findBySubject(subject);
        for (SubjectStudentEntity rel : relations) {
            Long studentId = rel.getStudent().getId();
            studentTestRepository.findByStudent_IdAndTest_Id(studentId, saved.getId())
                    .orElseGet(() -> studentTestRepository.save(new StudentTestEntity(null, rel.getStudent(), saved)));
        }

        return saved;
    }

    @Override
    public TestsEntity updateTest(Long id, Integer time, String name, Long subjectId) {
        TestsEntity test = testsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        if (time != null) {
            test.setTime(time);
        }
        if (name != null) {
            test.setName(name);
        }
        if (subjectId != null) {
            SubjectsEntity subject = subjectsRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            test.setSubject(subject);
        }

        return testsRepository.save(test);
    }

    @Override
    public TestsEntity getTestById(Long id) {
        return testsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public List<TestsEntity> getTestsBySubject(Long subjectId) {
        return testsRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public void delete(Long id) {
        testsRepository.deleteById(id);
    }
}
