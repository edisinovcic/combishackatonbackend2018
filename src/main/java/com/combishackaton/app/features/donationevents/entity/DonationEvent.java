package com.combishackaton.app.features.donationevents.entity;

import com.combishackaton.app.common.model.BaseEntity;
import com.combishackaton.app.features.userdonations.entity.UserDonation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "donation_events")
@Data
public class DonationEvent extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "user_donations")
    private List<UserDonation> userDonations;


}
