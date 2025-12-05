package com.example.testingsystemback.enteties;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class SubjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Один преподаватель у предмета
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private UsersEntity teacher;

    // Много студентов — через таблицу subject_student
    @OneToMany(mappedBy = "subject")
    private List<SubjectStudentEntity> students;

    public SubjectsEntity() {}

    public SubjectsEntity(
            Long id,
            String name,
            UsersEntity teacher
    ) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
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

    public List<SubjectStudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<SubjectStudentEntity> students) {
        this.students = students;
    }
}
