package com.combishackaton.app.features.donationevents.controller;

import com.combishackaton.app.common.exception.InsufficientPriviledgesException;
import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.common.validation.AuthenticationValidator;
import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.features.donationevents.model.DonationEventInviteTriggerResponse;
import com.combishackaton.app.features.donationevents.model.DonationEventRequest;
import com.combishackaton.app.features.donationevents.service.DonationEventsService;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/donation-events")
public class DonationEventController {


    private DonationEventsService donationEventsService;
    private UserService userService;
    private AuthenticationValidator authenticationValidator;

    @Autowired
    public DonationEventController(DonationEventsService donationEventsService, UserService userService,
            AuthenticationValidator authenticationValidator) {
        this.donationEventsService = donationEventsService;
        this.userService = userService;
        this.authenticationValidator = authenticationValidator;
    }

    @GetMapping
    public RestResponse<List<DonationEvent>> fetchAll() throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<DonationEvent>>(true).setData(donationEventsService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<DonationEvent> findOne(@PathVariable(value = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<DonationEvent>(true).setData(donationEventsService.findOne(id));
    }


    @PutMapping("/{id}")
    public RestResponse<DonationEventInviteTriggerResponse> triggerEvent(@PathVariable(name = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<DonationEventInviteTriggerResponse>(true)
                .setData(donationEventsService.executeInvite(id));
    }

    @PostMapping
    public RestResponse<DonationEvent> create(@RequestBody DonationEventRequest donationEventRequest) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<DonationEvent>(true).setData(donationEventsService.create(donationEventRequest));
    }

}
