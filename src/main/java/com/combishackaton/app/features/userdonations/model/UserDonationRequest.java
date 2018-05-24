package com.combishackaton.app.features.userdonations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDonationRequest {

    private String email;
    private String description;
}
