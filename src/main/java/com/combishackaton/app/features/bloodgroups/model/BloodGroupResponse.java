package com.combishackaton.app.features.bloodgroups.model;

import com.combishackaton.app.features.bloodgroups.entity.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.combishackaton.app.common.Constants.convertListToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupResponse {

    private String name;
    private List<String> accepts;
    private List<String> gives;


    public BloodGroup toBloodGroup() {
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setAccepts(convertListToString(getAccepts()));
        bloodGroup.setGives(convertListToString(getGives()));
        bloodGroup.setName(getName());
        return bloodGroup;
    }

}
