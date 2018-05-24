package com.combishackaton.app.features.answers.controller;

import com.combishackaton.app.common.exception.InsufficientPriviledgesException;
import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.common.validation.AuthenticationValidator;
import com.combishackaton.app.features.answers.entity.Answer;
import com.combishackaton.app.features.answers.model.AnswerRegistrationRequest;
import com.combishackaton.app.features.answers.service.AnswerService;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private AnswerService answerService;
    private UserService userService;
    private AuthenticationValidator authenticationValidator;


    @Autowired
    public AnswerController(AnswerService answerService, UserService userService,
            AuthenticationValidator authenticationValidator) {
        this.answerService = answerService;
        this.userService = userService;
        this.authenticationValidator = authenticationValidator;
    }

    @GetMapping
    public RestResponse<List<Answer>> fetchAll() throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<Answer>>(true).setData(answerService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<Answer> fetchOne(@PathVariable(value = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<Answer>(true).setData(answerService.findById(id));
    }

    @GetMapping("/user/{id}")
    public RestResponse<List<Answer>> fetchByUser(@PathVariable(value = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<Answer>>(true).setData(answerService.findAllByUser(id));
    }

    @GetMapping("/question/{id}")
    public RestResponse<List<Answer>> fetchByQuestion(@PathVariable(value = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<Answer>>(true).setData(answerService.findAllByQuestion(id));
    }

    @GetMapping("/authenticated")
    public RestResponse<List<Answer>> fetchByAuthenticatedUser() {
        return new RestResponse<List<Answer>>(true)
                .setData(answerService.findAllByUser(userService.getAuthenticatedUser().getId()));
    }

    @PostMapping
    public RestResponse<Answer> create(@RequestBody AnswerRegistrationRequest answerRegistrationRequest) throws
            UserDoesntExistException {
        return new RestResponse<Answer>(true).setData(answerService.create(answerRegistrationRequest));
    }
}
