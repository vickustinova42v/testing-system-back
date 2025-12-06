package com.example.testingsystemback.enteties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "fathers_name")
    private String fathersName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RolesEntity role;

    // Предметы, где пользователь является преподавателем
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<SubjectsEntity> subjectsAsTeacher;

    // Предметы, где пользователь студент (через таблицу subject_student)
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<SubjectStudentEntity> subjectsAsStudent;

    public UsersEntity() {}

    public UsersEntity(
            Long id,
            String lastName,
            String firstName,
            String fathersName,
            String email,
            String password,
            RolesEntity role
    ) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fathersName = fathersName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesEntity getRole() {
        return role;
    }

    public void setRole(RolesEntity role) {
        this.role = role;
    }

    public List<SubjectsEntity> getSubjectsAsTeacher() {
        return subjectsAsTeacher;
    }

    public void setSubjectsAsTeacher(List<SubjectsEntity> subjectsAsTeacher) {
        this.subjectsAsTeacher = subjectsAsTeacher;
    }

    public List<SubjectStudentEntity> getSubjectsAsStudent() {
        return subjectsAsStudent;
    }

    public void setSubjectsAsStudent(List<SubjectStudentEntity> subjectsAsStudent) {
        this.subjectsAsStudent = subjectsAsStudent;
    }
}
