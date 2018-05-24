package com.combishackaton.app.features.invites.service;

import com.combishackaton.app.features.invites.entity.Invite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InviteServiceImpl implements InviteService {

    private InviteRepository inviteRepository;

    @Autowired
    public InviteServiceImpl(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
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
}
