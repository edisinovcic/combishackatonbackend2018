package com.combishackaton.app.features.answers.service;

import com.combishackaton.app.features.answers.entity.Answer;
import com.combishackaton.app.features.answers.model.AnswerRegistrationRequest;
import com.combishackaton.app.user.exception.UserDoesntExistException;

import java.util.List;

public interface AnswerService {

    List<Answer> findAll();

    List<Answer> findAllByUser(String userId);

    List<Answer> findAllByQuestion(String questionId);

    Answer findById(String id);

    Answer create(AnswerRegistrationRequest answerRegistrationRequest) throws UserDoesntExistException;

}
