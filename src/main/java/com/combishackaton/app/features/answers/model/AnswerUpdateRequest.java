package com.combishackaton.app.features.answers.model;

import lombok.Data;

@Data
public class AnswerUpdateRequest {

    private String id;
    private String userId;
    private String questionId;
    private String answer;

}
