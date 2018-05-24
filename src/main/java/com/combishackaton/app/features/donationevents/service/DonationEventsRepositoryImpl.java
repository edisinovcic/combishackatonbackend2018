package com.combishackaton.app.features.donationevents.service;

import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.features.donationevents.model.DonationEventInviteTriggerResponse;
import com.combishackaton.app.features.donationevents.model.DonationEventRequest;
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

    @Override
    public DonationEventInviteTriggerResponse executeInvite(String id) {
        //TODO: Send invites
        DonationEvent donationEvent = findOne(id);
        DonationEventInviteTriggerResponse invite = new DonationEventInviteTriggerResponse();
        invite.setDescription(donationEvent.getDescription());
        invite.setId(donationEvent.getId());
        return invite;
    }

    @Override
    public DonationEvent create(DonationEventRequest donationEventRequest) {
        DonationEvent donationEvent = new DonationEvent();
        donationEvent.setAddress(donationEventRequest.address);
        donationEvent.setDescription(donationEventRequest.description);
        donationEvent.setLatitude(donationEventRequest.latitude);
        donationEvent.setLongitude(donationEventRequest.longitude);
        return donationEventRepository.save(donationEvent);
    }
}
