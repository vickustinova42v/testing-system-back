package com.example.testingsystemback;

import jakarta.persistence.*;

@Entity
@Table(name = "test_question")
public class TestQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id", nullable = false)
    private TestsEntity test;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private QuestionsEntity question;

    public TestQuestionEntity() {
    }

    public TestQuestionEntity(
        Long id,
        TestsEntity test,
        QuestionsEntity question
    ) {
        this.id = id;
        this.test = test;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestsEntity getTest() {
        return test;
    }

    public void setTest(TestsEntity test) {
        this.test = test;
    }

    public QuestionsEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionsEntity question) {
        this.question = question;
    }
}

