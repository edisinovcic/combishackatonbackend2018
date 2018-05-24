package com.combishackaton.app.features.questions.service;

import com.combishackaton.app.features.questions.entity.Question;
import com.combishackaton.app.features.questions.model.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findOne(String id) {
        return Optional.ofNullable(questionRepository.findOne(id))
                       .orElseThrow(() -> new EntityNotFoundException("Answer with id: " + id + " not found"));
    }

    @Override
    public Question save(QuestionResponse questionResponse) {
        return questionRepository.save(questionResponse.getTransferObject());
    }
}
