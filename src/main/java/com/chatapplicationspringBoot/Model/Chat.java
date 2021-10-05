package com.chatapplicationspringBoot.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_Chat")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Chat implements Serializable {
    @Id
    @Column(nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //Chat ID having a question and answer
    @Column(nullable = true, unique = true)
    private String question; //Chat question
    @Column(nullable = true)
    private String answer; //Chat answer
    @Column(nullable = true)
    private Date questionDate;
    @Column(nullable = true)
    private Date answerDate;

    public Chat() {
    }

    public Chat(long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }


}
