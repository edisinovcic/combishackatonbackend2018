package com.combishackaton.app.features.questions.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.questions.entity.Question;
import com.combishackaton.app.features.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public RestResponse<List<Question>> fetchAll() {
        return new RestResponse<List<Question>>(true).setData(questionService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<Question> findOne(@PathVariable(name = "id") String id) {
        return new RestResponse<Question>(true).setData(questionService.findOne(id));
    }


}
