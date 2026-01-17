package com.example.testingsystemback.interfaces.services;

import com.example.testingsystemback.enteties.SubjectsEntity;

import java.util.List;

public interface ISubjectsService {
    SubjectsEntity createSubject(String name, Long teacherId);
    List<SubjectsEntity> getAllSubjects();
    List<SubjectsEntity> getSubjectsByTeacher(Long teacherId);
    void delete(Long id);
}
