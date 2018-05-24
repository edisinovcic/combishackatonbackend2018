package com.combishackaton.app.features.invites.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.invites.entity.Invite;
import com.combishackaton.app.features.invites.service.InviteService;
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


}
