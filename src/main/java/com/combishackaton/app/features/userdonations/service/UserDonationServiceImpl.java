package com.combishackaton.app.features.userdonations.service;

import com.combishackaton.app.features.userdonations.entity.UserDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserDonationServiceImpl implements UserDonationService {

    private UserDonationRepository userDonationRepository;

    @Autowired
    public UserDonationServiceImpl(UserDonationRepository userDonationRepository) {
        this.userDonationRepository = userDonationRepository;
    }

    @Override
    public List<UserDonation> findAll() {
        return userDonationRepository.findAll();
    }

    @Override
    public UserDonation findOne(String id) {
        return Optional.ofNullable(userDonationRepository.findOne(id)).orElseThrow(
                () -> new EntityNotFoundException("User donation with id: " + id + " does not exist!"));
    }

    @Override
    public List<UserDonation> findAllByUser(String id) {
        return userDonationRepository.findAllByUser(id);
    }
}
