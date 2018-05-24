package com.combishackaton.app.features.questions.model;

import com.combishackaton.app.features.questions.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    @Column(name = "description")
    private String description;


}
