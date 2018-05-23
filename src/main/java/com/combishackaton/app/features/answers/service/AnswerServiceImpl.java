package com.combishackaton.app.features.answers.service;

import com.combishackaton.app.features.answers.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {


    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
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
}
