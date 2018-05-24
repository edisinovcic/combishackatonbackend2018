package com.combishackaton.app.features.donationevents.service;

import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.features.donationevents.model.DonationEventInviteTriggerResponse;
import com.combishackaton.app.features.donationevents.model.DonationEventRequest;
import com.combishackaton.app.features.invites.model.InviteRegisterRequest;
import com.combishackaton.app.features.invites.service.InviteService;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonationEventsRepositoryImpl implements DonationEventsService {

    private DonationEventRepository donationEventRepository;
    private InviteService inviteService;
    private UserService userService;

    @Autowired
    public DonationEventsRepositoryImpl(DonationEventRepository donationEventRepository, InviteService inviteService,
            UserService userService) {
        this.donationEventRepository = donationEventRepository;
        this.inviteService = inviteService;
        this.userService = userService;
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
    public DonationEvent create(DonationEventRequest donationEventRequest) throws UserDoesntExistException {
        DonationEvent donationEvent = new DonationEvent();
        donationEvent.setAddress(donationEventRequest.address);
        donationEvent.setDescription(donationEventRequest.description);
        donationEvent.setLatitude(donationEventRequest.latitude);
        donationEvent.setLongitude(donationEventRequest.longitude);

        //create invite
        List<InviteRegisterRequest> inviteRegisterRequests = sendInvitesToUsers(donationEvent.getDescription());
        for(InviteRegisterRequest inviteRegisterRequest : inviteRegisterRequests){
            inviteService.create(inviteRegisterRequest);
        }

        return donationEventRepository.save(donationEvent);
    }

    private List<InviteRegisterRequest> sendInvitesToUsers(String eventDescription) {
        List<User> users = userService.findAll();
        List<InviteRegisterRequest> inviteRegisterRequests = new ArrayList<>();
        for(User user : users){
            InviteRegisterRequest inviteRegisterRequest = new InviteRegisterRequest();
            inviteRegisterRequest.setUserId(user.getId());
            inviteRegisterRequest.setDescription(eventDescription);
            inviteRegisterRequests.add(inviteRegisterRequest);
        }
        return inviteRegisterRequests;

    }
}
