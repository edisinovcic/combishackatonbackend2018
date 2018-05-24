package com.combishackaton.app.features.userdonations.service;

import com.combishackaton.app.features.bloodstocks.model.BloodStockRequest;
import com.combishackaton.app.features.bloodstocks.service.BloodStockService;
import com.combishackaton.app.features.userdonations.entity.UserDonation;
import com.combishackaton.app.features.userdonations.model.UserDonationRequest;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserDonationServiceImpl implements UserDonationService {

    private UserDonationRepository userDonationRepository;
    private UserService userService;
    private BloodStockService bloodStockService;

    @Autowired
    public UserDonationServiceImpl(UserDonationRepository userDonationRepository, UserService userService,
            BloodStockService bloodStockService) {
        this.userDonationRepository = userDonationRepository;
        this.userService = userService;
        this.bloodStockService = bloodStockService;
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
        return userDonationRepository.findAllByUserId(id);
    }

    @Override
    public UserDonation create(UserDonationRequest userDonationRequest) throws UserDoesntExistException {
        UserDonation userDonation = new UserDonation();
        userDonation.setDescription(userDonationRequest.getDescription());
        userDonation.setUser(userService.findUserByEmail(userDonationRequest.getEmail()));

        BloodStockRequest bloodStockRequest = new BloodStockRequest();
        bloodStockRequest.setBloodGroup(userDonation.getUser().getBloodType());
        bloodStockRequest.setAmount(1);
        bloodStockService.create(bloodStockRequest);

        return userDonationRepository.save(userDonation);
    }
}
