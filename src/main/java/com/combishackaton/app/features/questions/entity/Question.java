package com.combishackaton.app.features.questions.entity;

import com.combishackaton.app.common.model.TimeEntity;
import com.combishackaton.app.common.model.TransferEntity;
import com.combishackaton.app.features.questions.model.QuestionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "questions")
@Data
public class Question extends TimeEntity implements TransferEntity<QuestionResponse> {

    @Column(name = "description")
    private String description;

    @Override
    public QuestionResponse getTransferObject() {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setDescription(getDescription());
        return questionResponse;
    }
}
