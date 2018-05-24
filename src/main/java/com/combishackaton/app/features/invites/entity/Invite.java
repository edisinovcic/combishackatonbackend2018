package com.combishackaton.app.features.invites.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import com.combishackaton.app.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "invites")
@Data
public class Invite extends AuditableEntity {

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "answer_type")
    private String answerType;

    @Column(name = "answer_text")
    private String answerText;

    @JoinColumn(name = "donation_event_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private DonationEvent donationEvent;

}
