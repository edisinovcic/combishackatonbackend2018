package com.combishackaton.app.features.bloodstocks.model;

import com.combishackaton.app.features.bloodstocks.entity.BloodStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodStockRequest {

    private String bloodGroup;
    private Integer amount;

    public BloodStock toBloodStock() {
        BloodStock bloodStock = new BloodStock();
        bloodStock.setAmount(getAmount());
        bloodStock.setBloodGroup(getBloodGroup());
        return bloodStock;
    }

}


