package com.combishackaton.app.features.donationevents.model;

import com.combishackaton.app.user.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class DonationEventInviteTriggerResponse {

    String id;
    String description;
    List<User> users;

}
