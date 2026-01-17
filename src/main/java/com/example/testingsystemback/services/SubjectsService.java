package com.example.testingsystemback.services;

import com.example.testingsystemback.interfaces.services.ISubjectsService;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.SubjectsRepository;
import com.example.testingsystemback.repositories.SubjectStudentRepository;
import com.example.testingsystemback.repositories.UsersRepository;
import com.example.testingsystemback.repositories.TestsRepository;
import com.example.testingsystemback.repositories.StudentTestRepository;
import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.enteties.StudentTestEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService implements ISubjectsService {

    private final SubjectsRepository subjectsRepository;
    private final UsersRepository usersRepository;
    private final SubjectStudentRepository subjectStudentRepository;
    private final TestsRepository testsRepository;
    private final StudentTestRepository studentTestRepository;

    public SubjectsService(
            SubjectsRepository subjectsRepository,
            UsersRepository usersRepository,
            SubjectStudentRepository subjectStudentRepository,
            TestsRepository testsRepository,
            StudentTestRepository studentTestRepository
    ) {
        this.subjectsRepository = subjectsRepository;
        this.usersRepository = usersRepository;
        this.subjectStudentRepository = subjectStudentRepository;
        this.testsRepository = testsRepository;
        this.studentTestRepository = studentTestRepository;
    }

    @Override
    public List<SubjectsEntity> getAllSubjects() {
        return subjectsRepository.findAll();
    }

    @Override
    public SubjectsEntity getSubjectById(Long id) {
        return subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));
    }

    @Override
    public SubjectsEntity createSubject(String name, Long teacherId) {
        UsersEntity teacher = usersRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Преподаватель не найден"));

        SubjectsEntity subject = new SubjectsEntity();
        subject.setName(name);
        subject.setTeacher(teacher);

        return subjectsRepository.save(subject);
    }

    @Override
    public SubjectsEntity updateSubject(Long id, String name, Long teacherId) {
        SubjectsEntity subject = subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        subject.setName(name);

        if (teacherId != null) {
            UsersEntity teacher = usersRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Преподаватель не найден"));
            subject.setTeacher(teacher);
        }

        return subjectsRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        SubjectsEntity subject = subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        List<SubjectStudentEntity> list = subjectStudentRepository.findBySubject(subject);
        subjectStudentRepository.deleteAll(list);
        subjectsRepository.delete(subject);
    }

    @Override
    public void addStudentToSubject(Long subjectId, Long studentId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        UsersEntity student = usersRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));

        boolean exists = subjectStudentRepository
                .findBySubjectAndStudent(subject, student)
                .isPresent();

        if (exists) {
            throw new RuntimeException("Студент уже добавлен в предмет");
        }

        SubjectStudentEntity relation = new SubjectStudentEntity();
        relation.setSubject(subject);
        relation.setStudent(student);

        subjectStudentRepository.save(relation);

        List<TestsEntity> tests = testsRepository.findAllBySubjectId(subjectId);
        for (TestsEntity test : tests) {
            studentTestRepository.findByStudent_IdAndTest_Id(studentId, test.getId())
                    .orElseGet(() -> studentTestRepository.save(new StudentTestEntity(null, student, test)));
        }
    }

    @Override
    public void removeStudentFromSubject(Long subjectId, Long studentId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        UsersEntity student = usersRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));

        SubjectStudentEntity relation = subjectStudentRepository
                .findBySubjectAndStudent(subject, student)
                .orElseThrow(() -> new RuntimeException("Студент не записан на предмет"));

        subjectStudentRepository.delete(relation);
    }

    @Override
    public List<SubjectStudentEntity> getStudentsOfSubject(Long subjectId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        return subjectStudentRepository.findBySubject(subject);
    }

    @Override
    public List<SubjectsEntity> getSubjectsByTeacher(Long teacherId) {
        usersRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Преподаватель не найден"));
        return subjectsRepository.findAllByTeacherId(teacherId);
    }

    @Override
    public List<SubjectsEntity> getSubjectsByStudent(Long studentId) {
        usersRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
        return subjectsRepository.findAllByStudentId(studentId);
    }
}
