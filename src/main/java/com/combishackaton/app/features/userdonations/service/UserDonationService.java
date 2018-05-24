package com.combishackaton.app.features.userdonations.service;

import com.combishackaton.app.features.userdonations.entity.UserDonation;
import com.combishackaton.app.features.userdonations.model.UserDonationRequest;
import com.combishackaton.app.user.exception.UserDoesntExistException;

import java.util.List;

public interface UserDonationService {

    List<UserDonation> findAll();

    UserDonation findOne(String id);

    List<UserDonation> findAllByUser(String id);

    UserDonation create(UserDonationRequest userDonationRequest) throws UserDoesntExistException;
}
