package com.combishackaton.app.features.questions.service;

import com.combishackaton.app.features.questions.entity.Question;
import com.combishackaton.app.features.questions.model.QuestionResponse;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    Question findOne(String id);

    Question save(QuestionResponse questionResponse);

}
