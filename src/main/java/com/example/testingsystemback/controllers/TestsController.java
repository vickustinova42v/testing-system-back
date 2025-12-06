package com.example.testingsystemback.controllers;

import com.example.testingsystemback.enteties.TestsEntity;
import com.example.testingsystemback.services.TestsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestsController {

    private final TestsService testsService;

    public TestsController(TestsService testsService) {
        this.testsService = testsService;
    }

    @PostMapping
    public TestsEntity create(@RequestParam Integer time,
                              @RequestParam String name,
                              @RequestParam Long subjectId) {
        return testsService.createTest(time, name, subjectId);
    }

    @GetMapping("/subject/{id}")
    public List<TestsEntity> getBySubject(@PathVariable Long id) {
        return testsService.getTestsBySubject(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testsService.delete(id);
    }
}
