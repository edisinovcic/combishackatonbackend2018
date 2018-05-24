package com.combishackaton.app.features.userdonations.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_donations")
@Data
public class UserDonation extends AuditableEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "user_donations_donation_events",
            joinColumns = { @JoinColumn(name = "donation_event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_donation_id") })
    private List<DonationEvent> donationEvents;

}

