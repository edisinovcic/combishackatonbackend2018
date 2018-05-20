package com.combishackaton.app.user.service;


import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.exception.UserAlreadyExistsException;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.model.UserRegistrationRequest;
import com.combishackaton.app.user.model.UserUpdateRequest;

public interface UserService {

    User getAuthenticatedUser();

    User createUser(UserRegistrationRequest userRegistrationRequest);

    User registerUser(UserRegistrationRequest userRegistrationRequest) throws UserAlreadyExistsException;

    User updateUser(UserUpdateRequest userUpdateRequest);

    User findUserByEmail(String email) throws UserDoesntExistException;

    void delete();

    void deleteAll();

    User findUserById(String id) throws UserDoesntExistException;


}
