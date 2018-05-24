package com.combishackaton.app.features.userdonations.service;

import com.combishackaton.app.features.userdonations.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDonationRepository extends JpaRepository<UserDonation, String> {

    List<UserDonation> findAllByUserId(String id);


}
