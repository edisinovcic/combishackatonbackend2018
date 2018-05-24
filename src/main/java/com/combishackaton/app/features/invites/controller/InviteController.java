package com.combishackaton.app.features.invites.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.invites.entity.Invite;
import com.combishackaton.app.features.invites.model.InviteRegisterRequest;
import com.combishackaton.app.features.invites.model.InviteUpdateRequest;
import com.combishackaton.app.features.invites.service.InviteService;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invites")
public class InviteController {

    private InviteService inviteService;

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @GetMapping
    public RestResponse<List<Invite>> fetchAll() {
        return new RestResponse<List<Invite>>(true).setData(inviteService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<Invite> fetchOne(@PathVariable(name = "id") String id) {
        return new RestResponse<Invite>(true).setData(inviteService.findOne(id));
    }

    @GetMapping("/user/{id}")
    public RestResponse<List<Invite>> fetchInvitesForUser(@PathVariable(name = "id") String id) {
        return new RestResponse<List<Invite>>(true).setData(inviteService.findByUser(id));
    }

    @GetMapping("/event/{id}")
    public RestResponse<List<Invite>> fetchInvitesForEvent(@PathVariable(name = "id") String id) {
        return new RestResponse<List<Invite>>(true).setData(inviteService.findByEvent(id));
    }

    @PostMapping
    public RestResponse<Invite> create(@RequestBody InviteRegisterRequest inviteRegisterRequest) throws
            UserDoesntExistException {
        return new RestResponse<Invite>(true).setData(inviteService.create(inviteRegisterRequest));
    }

    @PutMapping("/{id}")
    public RestResponse<Invite> update(@PathVariable(value = "id") String id, @RequestBody InviteUpdateRequest
            inviteUpdateRequest) throws UserDoesntExistException {
        return new RestResponse<Invite>(true).setData(inviteService.update(inviteUpdateRequest, id));
    }

}
