package com.example.testingsystemback;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class AnswersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_right_answer", nullable = false)
    private Boolean isRightAnswer;

    // question_id → questions.id (NOT NULL)
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private QuestionsEntity question;

    public AnswersEntity() {
    }

    public AnswersEntity(
        Long id,
        String name,
        Boolean isRightAnswer,
        QuestionsEntity question
    ) {
        this.id = id;
        this.name = name;
        this.isRightAnswer = isRightAnswer;
        this.question = question;
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

    public Boolean getIsRightAnswer() {
        return isRightAnswer;
    }

    public void setIsRightAnswer(Boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }

    public QuestionsEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionsEntity question) {
        this.question = question;
    }
}
