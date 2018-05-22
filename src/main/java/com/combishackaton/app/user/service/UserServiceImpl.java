package com.combishackaton.app.user.service;

import com.combishackaton.app.security.auth.model.UserPrincipal;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.exception.UserAlreadyExistsException;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.model.UserConstants;
import com.combishackaton.app.user.model.UserRegistrationRequest;
import com.combishackaton.app.user.model.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getAuthenticatedUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                                                                           .getPrincipal();
        return Optional.ofNullable(userRepository.findById(userPrincipal.getId()))
                       .orElseThrow(IllegalStateException::new);
    }

    @Override
    public User registerUser(UserRegistrationRequest userRegistrationRequest) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(userRegistrationRequest.getEmail()) != null) {
            throw new UserAlreadyExistsException(UserConstants.USER_ALREADY_EXISTS,
                    UserConstants.USER_ALREADY_EXISTS_CODE);
        }

        return createUser(userRegistrationRequest);
    }

    @Override
    public User createUser(UserRegistrationRequest userRegistrationRequest) {
        User user = userRegistrationRequest.fillUser();
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userUpdateRequest.toUser(getAuthenticatedUser());
        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) throws UserDoesntExistException {
        return Optional.ofNullable(userRepository.findByEmail(email))
                       .orElseThrow(() -> new UserDoesntExistException("User with " + email + " doesn't exist!"));
    }

    @Override
    public void delete() {
        User user = getAuthenticatedUser();
        userRepository.delete(user.getId());
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findUserById(String id) throws UserDoesntExistException {
        return Optional.ofNullable(userRepository.findById(id))
                       .orElseThrow(() -> new UserDoesntExistException("User doesn't exist!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
