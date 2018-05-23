package com.combishackaton.app.features.bloodgroup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupResponse {

    private String name;
    private List<String> accepts;
    private List<String> gives;

}
