package com.combishackaton.app.features.donationevents.service;


import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.features.donationevents.model.DonationEventRequest;
import com.combishackaton.app.features.donationevents.model.DonationEventInviteTriggerResponse;

import java.util.List;

public interface DonationEventsService {

    List<DonationEvent> findAll();

    DonationEvent findOne(String id);

    DonationEventInviteTriggerResponse executeInvite(String id);

    DonationEvent create(DonationEventRequest donationEventRequest);

}
