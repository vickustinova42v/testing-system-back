package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.repositories.TestsRepository;
import com.example.testingsystemback.repositories.SubjectsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService {

    private final TestsRepository testsRepository;
    private final SubjectsRepository subjectsRepository;

    public TestsService(TestsRepository testsRepository,
                        SubjectsRepository subjectsRepository) {
        this.testsRepository = testsRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public TestsEntity createTest(Integer time, String name, Long subjectId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        TestsEntity test = new TestsEntity();
        test.setTime(time);
        test.setName(name);
        test.setSubject(subject);

        return testsRepository.save(test);
    }

    public List<TestsEntity> getTestsBySubject(Long subjectId) {
        return testsRepository.findAllBySubjectId(subjectId);
    }

    public void delete(Long id) {
        testsRepository.deleteById(id);
    }
}
