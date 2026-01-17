package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;

import java.util.List;

public interface ISubjectsService {
    List<SubjectsEntity> getAllSubjects();
    SubjectsEntity getSubjectById(Long id);

    SubjectsEntity createSubject(String name, Long teacherId);
    SubjectsEntity updateSubject(Long id, String name, Long teacherId);

    void delete(Long id);

    void addStudentToSubject(Long subjectId, Long studentId);
    void removeStudentFromSubject(Long subjectId, Long studentId);
    List<SubjectStudentEntity> getStudentsOfSubject(Long subjectId);

    List<SubjectsEntity> getSubjectsByTeacher(Long teacherId);

    List<SubjectsEntity> getSubjectsByStudent(Long studentId);
}
