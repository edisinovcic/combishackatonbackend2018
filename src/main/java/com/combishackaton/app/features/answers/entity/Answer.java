package com.combishackaton.app.features.answers.entity;

import com.combishackaton.app.common.model.TimeEntity;
import com.combishackaton.app.features.questions.entity.Question;
import com.combishackaton.app.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "answers")
@Data
public class Answer extends TimeEntity {

    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
