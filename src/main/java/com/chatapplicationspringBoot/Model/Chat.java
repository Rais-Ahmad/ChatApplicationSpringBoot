package com.chatapplicationspringBoot.Model;

import javax.persistence.*;

@Entity
@Table(name = "t_Chat")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Chat {
    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Description Chat POJO class
     */
    @Id
    @Column(nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //Chat ID having a question and answer
    @Column(nullable = true, unique = true)
    private String question; //Chat question
    @Column(nullable = true)
    private String answer; //Chat answer
    @Column(nullable = true)
    private String questionDate;
    @Column(nullable = true)
    private String answerDate;

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

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }


}
