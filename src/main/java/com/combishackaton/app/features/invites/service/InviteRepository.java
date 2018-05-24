package com.combishackaton.app.features.invites.service;

import com.combishackaton.app.features.invites.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite, String> {
}
