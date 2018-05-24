package com.combishackaton.app.features.donationevents.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.features.donationevents.model.DonationEventRequest;
import com.combishackaton.app.features.donationevents.model.DonationEventInviteTriggerResponse;
import com.combishackaton.app.features.donationevents.service.DonationEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/donation-events")
public class DonationEventController {


    private DonationEventsService donationEventsService;

    @Autowired
    public DonationEventController(DonationEventsService donationEventsService) {
        this.donationEventsService = donationEventsService;
    }


    @GetMapping
    public RestResponse<List<DonationEvent>> fetchAll() {
        return new RestResponse<List<DonationEvent>>(true).setData(donationEventsService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<DonationEvent> findOne(@PathVariable(value = "id") String id) {
        return new RestResponse<DonationEvent>(true).setData(donationEventsService.findOne(id));
    }


    @PutMapping("/{id}")
    public RestResponse<DonationEventInviteTriggerResponse> triggerEvent(@PathVariable(name = "id") String id) {
        return new RestResponse<DonationEventInviteTriggerResponse>(true).setData(donationEventsService.executeInvite(id));
    }

    @PostMapping
    public RestResponse<DonationEvent> triggerEvent(@RequestBody DonationEventRequest donationEventRequest) {
        return new RestResponse<DonationEvent>(true).setData(donationEventsService.create(donationEventRequest));
    }

}
