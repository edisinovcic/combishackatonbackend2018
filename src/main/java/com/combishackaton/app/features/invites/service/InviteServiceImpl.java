package com.combishackaton.app.features.invites.service;

import com.combishackaton.app.features.invites.entity.Invite;
import com.combishackaton.app.features.invites.model.InviteRegisterRequest;
import com.combishackaton.app.features.invites.model.InviteUpdateRequest;
import com.combishackaton.app.user.exception.UserDoesntExistException;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InviteServiceImpl implements InviteService {

    private InviteRepository inviteRepository;
    private UserService userService;

    @Autowired
    public InviteServiceImpl(InviteRepository inviteRepository, UserService userService) {
        this.inviteRepository = inviteRepository;
        this.userService = userService;
    }

    @Override
    public List<Invite> findAll() {
        return inviteRepository.findAll();
    }

    @Override
    public Invite findOne(String id) {
        return Optional.ofNullable(inviteRepository.findOne(id))
                       .orElseThrow(() -> new EntityNotFoundException("Invite with id: " + id + " not found"));
    }

    @Override
    public Invite create(InviteRegisterRequest inviteRegisterRequest) throws UserDoesntExistException {
        Invite invite = new Invite();
        invite.setDescription(inviteRegisterRequest.getDescription());
        invite.setUser(userService.findUserById(inviteRegisterRequest.getUserId()));
        return invite;
    }

    @Override
    public Invite update(InviteUpdateRequest inviteUpdateRequest, String id) throws UserDoesntExistException {
        Invite oldInvite = inviteRepository.findOne(id);
        oldInvite.setUser(userService.findUserById(inviteUpdateRequest.getUserId()));
        oldInvite.setDescription(inviteUpdateRequest.getDescription());
        return inviteRepository.save(oldInvite);
    }
}
