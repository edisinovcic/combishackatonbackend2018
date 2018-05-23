package com.combishackaton.app.features.donationevents.service;


import com.combishackaton.app.features.donationevents.entity.DonationEvent;

import java.util.List;

public interface DonationEventsService {

    List<DonationEvent> findAll();

    DonationEvent findOne(String id);

}
