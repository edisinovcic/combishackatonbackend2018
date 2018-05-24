package com.combishackaton.app.features.userdonations.entity;

import com.combishackaton.app.common.model.TimeEntity;
import com.combishackaton.app.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_donations")
@Data
public class UserDonation extends TimeEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "description")
    private String description;
}
