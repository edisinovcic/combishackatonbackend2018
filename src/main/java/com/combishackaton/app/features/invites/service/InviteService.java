package com.combishackaton.app.features.invites.service;

import com.combishackaton.app.features.invites.entity.Invite;
import com.combishackaton.app.features.invites.model.InviteRegisterRequest;
import com.combishackaton.app.features.invites.model.InviteUpdateRequest;
import com.combishackaton.app.user.exception.UserDoesntExistException;

import java.util.List;

public interface InviteService {

    List<Invite> findAll();

    Invite findOne(String id);

    Invite create(InviteRegisterRequest inviteRegisterRequest) throws UserDoesntExistException;

    Invite update(InviteUpdateRequest inviteUpdateRequest, String id) throws UserDoesntExistException;

}
