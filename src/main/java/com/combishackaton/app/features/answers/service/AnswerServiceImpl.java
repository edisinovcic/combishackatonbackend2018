package com.combishackaton.app.features.answers.service;

import com.combishackaton.app.features.answers.entity.Answer;
import com.combishackaton.app.features.answers.model.AnswerRegistrationRequest;
import com.combishackaton.app.features.questions.service.QuestionService;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {


    private AnswerRepository answerRepository;
    private QuestionService questionService;
    private UserService userService;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionService questionService,
            UserService userService) {
        this.answerRepository = answerRepository;
        this.userService = userService;
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> findAllByUser(String userId) {
        return answerRepository.findAllByUserId(userId);
    }

    @Override
    public List<Answer> findAllByQuestion(String questionId) {
        return answerRepository.findAllByQuestion(questionId);
    }

    @Override
    public Answer findById(String id) {
        return Optional.ofNullable(answerRepository.findOne(id))
                       .orElseThrow(() -> new EntityNotFoundException("Answer with id: " + id + " not found"));
    }

    @Override
    public Answer create(AnswerRegistrationRequest answerRegistrationRequest) throws UserDoesntExistException {
        Answer answer = new Answer();
        answer.setAnswer(answerRegistrationRequest.getAnswer());
        answer.setQuestion(questionService.findOne(answerRegistrationRequest.getQuestionId()));
        answer.setUser(userService.findUserById(answerRegistrationRequest.getUserId()));
        return answerRepository.save(answer);
    }


}
