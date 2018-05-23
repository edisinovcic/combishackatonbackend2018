package com.combishackaton.app.features.answers.service;

import com.combishackaton.app.features.answers.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {

    List<Answer> findAllByUserId(String id);

    List<Answer> findAllByQuestion(String id);

}
