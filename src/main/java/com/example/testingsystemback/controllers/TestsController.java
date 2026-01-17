package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.interfaces.services.ITestsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestsController {

    private final ITestsService testsService;

    public TestsController(ITestsService testsService) {
        this.testsService = testsService;
    }

    @PostMapping
    public TestsEntity create(
            @RequestParam Integer time,
            @RequestParam String name,
            @RequestParam Long subjectId
    ) {
        return testsService.createTest(time, name, subjectId);
    }

    @PutMapping("/{id}")
    public TestsEntity update(
            @PathVariable Long id,
            @RequestParam(required = false) Integer time,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long subjectId
    ) {
        return testsService.updateTest(id, time, name, subjectId);
    }

    @GetMapping("/{id}")
    public TestsEntity getById(@PathVariable Long id) {
        return testsService.getTestById(id);
    }

    @GetMapping("/subject/{subjectId}")
    public List<TestsEntity> getBySubject(@PathVariable Long subjectId) {
        return testsService.getTestsBySubject(subjectId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testsService.delete(id);
    }
}
