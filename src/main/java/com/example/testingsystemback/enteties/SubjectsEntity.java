package com.example.testingsystemback.enteties;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class SubjectsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private UsersEntity teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private UsersEntity student;

    public SubjectsEntity() {
    }

    public SubjectsEntity(
        Long id,
        String name,
        UsersEntity teacher,
        UsersEntity student
    ) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UsersEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(UsersEntity teacher) {
        this.teacher = teacher;
    }

    public UsersEntity getStudent() {
        return student;
    }

    public void setStudent(UsersEntity student) {
        this.student = student;
    }
}
