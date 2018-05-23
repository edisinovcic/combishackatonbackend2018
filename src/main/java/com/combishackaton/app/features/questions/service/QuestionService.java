package com.combishackaton.app.features.questions.service;

import com.combishackaton.app.features.questions.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    Question findOne(String id);

}
