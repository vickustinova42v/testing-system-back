package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.StudentTestEntity;
import com.example.testingsystemback.interfaces.services.IStudentTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student-test")
public class StudentTestController {

    private final IStudentTestService studentTestService;

    public StudentTestController(IStudentTestService studentTestService) {
        this.studentTestService = studentTestService;
    }

    @PostMapping("/submit")
    public StudentTestEntity submitTest(
            @RequestParam Long studentId,
            @RequestParam Long testId,
            @RequestBody Map<Long, List<Long>> studentAnswers
    ) {
        return studentTestService.submitTest(studentId, testId, studentAnswers);
    }
}
