package com.combishackaton.app.features.invites.service;

import com.combishackaton.app.features.invites.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteRepository extends JpaRepository<Invite, String> {
    List<Invite> findAllByUser_Id(String userId);

    List<Invite> findAllByDonationEvent_Id(String eventId);
}
