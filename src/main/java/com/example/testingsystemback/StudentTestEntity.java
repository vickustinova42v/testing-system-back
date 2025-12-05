package com.example.testingsystemback;

import jakarta.persistence.*;

@Entity
@Table(name = "student_test")
public class StudentTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mark", nullable = false)
    private Integer mark;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private UsersEntity student;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id", nullable = false)
    private TestsEntity test;

    public StudentTestEntity() {
    }

    public StudentTestEntity(
        Long id,
        Integer mark,
        UsersEntity student,
        TestsEntity test
    ) {
        this.id = id;
        this.mark = mark;
        this.student = student;
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public UsersEntity getStudent() {
        return student;
    }

    public void setStudent(UsersEntity student) {
        this.student = student;
    }

    public TestsEntity getTest() {
        return test;
    }

    public void setTest(TestsEntity test) {
        this.test = test;
    }
}
