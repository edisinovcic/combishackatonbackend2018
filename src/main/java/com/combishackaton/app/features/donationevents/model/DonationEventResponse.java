package com.combishackaton.app.features.donationevents.model;

import com.combishackaton.app.common.model.TransferEntity;
import com.combishackaton.app.features.donationevents.entity.DonationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationEventResponse implements TransferEntity<DonationEvent> {

    public String id;
    public String description;
    public String address;
    public String latitude;
    public String longitude;
    public String timestamp;


    @Override
    public DonationEvent getTransferObject() {
        return null;
    }
}
