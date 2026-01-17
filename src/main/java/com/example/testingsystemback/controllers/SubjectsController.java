package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.SubjectsEntity;
import com.example.testingsystemback.enteties.SubjectStudentEntity;
import com.example.testingsystemback.interfaces.services.ISubjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@CrossOrigin("*")
public class SubjectsController {

    private final ISubjectsService subjectsService;

    public SubjectsController(ISubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping
    public List<SubjectsEntity> getAllSubjects() {
        return subjectsService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectsEntity getSubjectById(@PathVariable Long id) {
        return subjectsService.getSubjectById(id);
    }

    @PostMapping
    public SubjectsEntity createSubject(
            @RequestParam String name,
            @RequestParam Long teacherId
    ) {
        return subjectsService.createSubject(name, teacherId);
    }

    @PutMapping("/{id}")
    public SubjectsEntity updateSubject(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(required = false) Long teacherId
    ) {
        return subjectsService.updateSubject(id, name, teacherId);
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectsService.delete(id);
        return "Предмет удалён";
    }

    @PostMapping("/{subjectId}/students/{studentId}")
    public String addStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        subjectsService.addStudentToSubject(subjectId, studentId);
        return "Студент добавлен";
    }

    @DeleteMapping("/{subjectId}/students/{studentId}")
    public String removeStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        subjectsService.removeStudentFromSubject(subjectId, studentId);
        return "Студент удалён";
    }

    @GetMapping("/{subjectId}/students")
    public List<SubjectStudentEntity> getStudents(
            @PathVariable Long subjectId
    ) {
        return subjectsService.getStudentsOfSubject(subjectId);
    }
}
