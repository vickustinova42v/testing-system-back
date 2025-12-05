package com.example.testingsystemback.enteties;

import jakarta.persistence.*;

@Entity
@Table(name = "subject_student")
public class SubjectStudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectsEntity subject;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private UsersEntity student;

    public SubjectStudentEntity() {}

    public SubjectStudentEntity(Long id, SubjectsEntity subject, UsersEntity student) {
        this.id = id;
        this.subject = subject;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public SubjectsEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectsEntity subject) {
        this.subject = subject;
    }

    public UsersEntity getStudent() {
        return student;
    }

    public void setStudent(UsersEntity student) {
        this.student = student;
    }
}
