package com.example.testingsystemback.services;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;
import com.example.testingsystemback.enteties.UsersEntity;
import com.example.testingsystemback.repositories.SubjectsRepository;
import com.example.testingsystemback.repositories.SubjectStudentRepository;
import com.example.testingsystemback.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {

    private final SubjectsRepository subjectsRepository;
    private final UsersRepository usersRepository;
    private final SubjectStudentRepository subjectStudentRepository;

    public SubjectsService(
            SubjectsRepository subjectsRepository,
            UsersRepository usersRepository,
            SubjectStudentRepository subjectStudentRepository
    ) {
        this.subjectsRepository = subjectsRepository;
        this.usersRepository = usersRepository;
        this.subjectStudentRepository = subjectStudentRepository;
    }

    // Получить все предметы
    public List<SubjectsEntity> getAllSubjects() {
        return subjectsRepository.findAll();
    }

    // Получить предмет по id
    public SubjectsEntity getSubjectById(Long id) {
        return subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));
    }

    // Создать предмет + сразу назначить преподавателя
    public SubjectsEntity createSubject(String name, Long teacherId) {
        UsersEntity teacher = usersRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Преподаватель не найден"));

        SubjectsEntity subject = new SubjectsEntity();
        subject.setName(name);
        subject.setTeacher(teacher);

        return subjectsRepository.save(subject);
    }

    // Обновить предмет (название + преподаватель)
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

    // Удалить предмет
    public void deleteSubject(Long id) {
        SubjectsEntity subject = subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        // удалить связи студент-предмет
        List<SubjectStudentEntity> list =
                subjectStudentRepository.findBySubject(subject);

        subjectStudentRepository.deleteAll(list);

        // удалить сам предмет
        subjectsRepository.delete(subject);
    }

    // Добавить студента к предмету
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
    }

    // Удалить студента из предмета
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

    // Получить всех студентов предмета
    public List<SubjectStudentEntity> getStudentsOfSubject(Long subjectId) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Предмет не найден"));

        return subjectStudentRepository.findBySubject(subject);
    }
}
