package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;
import com.example.testingsystemback.services.SubjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@CrossOrigin("*")
public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    // ---------- CRUD ДЛЯ ПРЕДМЕТОВ ----------

    // Получить все предметы
    @GetMapping
    public List<SubjectsEntity> getAllSubjects() {
        return subjectsService.getAllSubjects();
    }

    // Получить один предмет
    @GetMapping("/{id}")
    public SubjectsEntity getSubjectById(@PathVariable Long id) {
        return subjectsService.getSubjectById(id);
    }

    // Создать предмет
    @PostMapping
    public SubjectsEntity createSubject(
            @RequestParam String name,
            @RequestParam Long teacherId
    ) {
        return subjectsService.createSubject(name, teacherId);
    }

    // Обновить предмет
    @PutMapping("/{id}")
    public SubjectsEntity updateSubject(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(required = false) Long teacherId
    ) {
        return subjectsService.updateSubject(id, name, teacherId);
    }

    // Удалить предмет
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectsService.deleteSubject(id);
        return "Предмет удалён";
    }

    // ---------- СТУДЕНТЫ В ПРЕДМЕТЕ ----------

    // Добавить студента в предмет
    @PostMapping("/{subjectId}/students/{studentId}")
    public String addStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        subjectsService.addStudentToSubject(subjectId, studentId);
        return "Студент добавлен";
    }

    // Удалить студента из предмета
    @DeleteMapping("/{subjectId}/students/{studentId}")
    public String removeStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        subjectsService.removeStudentFromSubject(subjectId, studentId);
        return "Студент удалён";
    }

    // Получить всех студентов предмета
    @GetMapping("/{subjectId}/students")
    public List<SubjectStudentEntity> getStudents(
            @PathVariable Long subjectId
    ) {
        return subjectsService.getStudentsOfSubject(subjectId);
    }
}
