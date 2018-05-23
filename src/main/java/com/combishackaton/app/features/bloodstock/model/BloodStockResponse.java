package com.combishackaton.app.features.bloodstock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodStockResponse {

    private String bloodGroup;
    private Integer amount;

}
