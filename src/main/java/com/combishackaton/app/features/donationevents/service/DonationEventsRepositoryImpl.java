package com.combishackaton.app.features.donationevents.service;

import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DonationEventsRepositoryImpl implements DonationEventsService {

    private DonationEventRepository donationEventRepository;

    @Autowired
    public DonationEventsRepositoryImpl(DonationEventRepository donationEventRepository) {
        this.donationEventRepository = donationEventRepository;
    }

    @Override
    public List<DonationEvent> findAll() {
        return donationEventRepository.findAll();
    }

    @Override
    public DonationEvent findOne(String id) {
        return Optional.ofNullable(donationEventRepository.findOne(id)).orElseThrow(
                () -> new EntityNotFoundException("Donation event with id: " + id + " doesn't exist"));
    }
}
