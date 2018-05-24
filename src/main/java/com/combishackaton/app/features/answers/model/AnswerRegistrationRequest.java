package com.combishackaton.app.features.answers.model;

import lombok.Data;

@Data
public class AnswerRegistrationRequest {

    private String answer;
    private String userId;
    private String questionId;
}
