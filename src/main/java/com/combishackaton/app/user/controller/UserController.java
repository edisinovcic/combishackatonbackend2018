package com.combishackaton.app.user.controller;

import com.combishackaton.app.common.exception.UnauthorizedException;
import com.combishackaton.app.common.exception.ValidationException;
import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.common.social.oauth.OAuthService;
import com.combishackaton.app.logging.annotation.Loggable;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.exception.UserAlreadyExistsException;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.model.UserConstants;
import com.combishackaton.app.user.model.UserRegistrationRequest;
import com.combishackaton.app.user.model.UserResponse;
import com.combishackaton.app.user.model.UserUpdateRequest;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService, OAuthService oAuthService) {
        this.userService = userService;
    }

    @Loggable(action = "User registration")
    @PostMapping
    public RestResponse<UserResponse> create(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest,
            BindingResult bindingResult) throws ValidationException, UserAlreadyExistsException {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationException(UserConstants.VALIDATION_EXCEPTION, UserConstants.VALIDATION_EXCEPTION_CODE)
                    .setFieldErrors(bindingResult.getFieldErrors());
        }

        User user = userService.registerUser(userRegistrationRequest);
        return new RestResponse<UserResponse>(true).setData(user.getTransferObject());
    }

    @GetMapping("/authenticated")
    public RestResponse<UserResponse> fetch() {
        User user = userService.getAuthenticatedUser();
        return new RestResponse<UserResponse>(true).setData(user.getTransferObject());
    }


    @GetMapping("/{id}")
    public RestResponse<UserResponse> fetchByAdmin(@PathVariable(name = "id") String id) throws UnauthorizedException,
            UserDoesntExistException {
        User user = userService.getAuthenticatedUser();
        if(!user.getAuthorityGroup().contentEquals("ADMIN")) {
            throw new UnauthorizedException("You don't have priviledges high enough to access this endpoint!");
        }
        return new RestResponse<UserResponse>(true).setData(userService.findUserById(id).getTransferObject());
    }


    @GetMapping
    public RestResponse<List<UserResponse>> fetchAll() {
        List<User> users = userService.findAll();
        return new RestResponse<List<UserResponse>>(true)
                .setData(users.stream().map(User::getTransferObject).collect(Collectors.toList()));
    }


    @Loggable(action = "User updates profile")
    @PutMapping
    public RestResponse<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest,
            BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationException(UserConstants.VALIDATION_EXCEPTION, UserConstants.VALIDATION_EXCEPTION_CODE)
                    .setFieldErrors(bindingResult.getFieldErrors());
        }

        User user = userService.updateUser(userUpdateRequest);
        return new RestResponse<UserResponse>(true).setData(user.getTransferObject());
    }


    @Loggable(action = "User deletes profile")
    @DeleteMapping
    public RestResponse delete() {
        userService.delete();
        return new RestResponse(true);
    }

    @GetMapping("/exists")
    public RestResponse<UserResponse> getUserByEmail(@RequestParam String email) throws UserDoesntExistException {
        return new RestResponse<UserResponse>(true).setData(userService.findUserByEmail(email).getTransferObject());
    }
}
