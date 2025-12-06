package com.example.testingsystemback.enteties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "student_test")
public class StudentTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark", nullable = false)
    private Integer mark;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private UsersEntity student;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    @JsonIgnore
    private TestsEntity test;

    public StudentTestEntity() {}

    public StudentTestEntity(Integer mark, UsersEntity student, TestsEntity test) {
        this.mark = mark;
        this.student = student;
        this.test = test;
    }

    public Long getId() { return id; }
    public Integer getMark() { return mark; }
    public void setMark(Integer mark) { this.mark = mark; }
    public UsersEntity getStudent() { return student; }
    public void setStudent(UsersEntity student) { this.student = student; }
    public TestsEntity getTest() { return test; }
    public void setTest(TestsEntity test) { this.test = test; }
}
