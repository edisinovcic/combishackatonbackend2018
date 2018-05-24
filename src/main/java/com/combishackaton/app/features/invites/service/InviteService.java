package com.combishackaton.app.features.invites.service;

import com.combishackaton.app.features.invites.entity.Invite;

import java.util.List;

public interface InviteService {

    List<Invite> findAll();

    Invite findOne(String id);
    
}
