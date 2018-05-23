package com.combishackaton.app.features.donationevents.service;

import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationEventRepository extends JpaRepository<DonationEvent, String> {
}
