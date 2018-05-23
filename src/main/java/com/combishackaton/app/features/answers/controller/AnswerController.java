package com.combishackaton.app.features.answers.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.answers.entity.Answer;
import com.combishackaton.app.features.answers.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public RestResponse<List<Answer>> fetchAll() {
        return new RestResponse<List<Answer>>(true).setData(answerService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<Answer> fetchOne(@PathVariable(value = "id") String id) {
        return new RestResponse<Answer>(true).setData(answerService.findById(id));
    }

    @GetMapping("/user/{id}")
    public RestResponse<List<Answer>> fetchByUser(@PathVariable(value = "id") String id) {
        return new RestResponse<List<Answer>>(true).setData(answerService.findAllByUser(id));
    }

    @GetMapping("/question/{id}")
    public RestResponse<List<Answer>> fetchByQuestion(@PathVariable(value = "id") String id) {
        return new RestResponse<List<Answer>>(true).setData(answerService.findAllByQuestion(id));
    }
}
