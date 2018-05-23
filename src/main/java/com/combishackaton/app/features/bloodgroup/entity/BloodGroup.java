package com.combishackaton.app.features.bloodgroup.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import com.combishackaton.app.common.model.TransferEntity;
import com.combishackaton.app.features.bloodgroup.model.BloodGroupResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "blood_groups")
@Data
public class BloodGroup extends AuditableEntity implements TransferEntity<BloodGroupResponse> {

    @Column(name = "name")
    private String name;

    @Column(name = "accepts")
    private String accepts;

    @Column(name = "gives")
    private String gives;

    @Override
    public BloodGroupResponse getTransferObject() {
        BloodGroupResponse bloodGroupResponse = new BloodGroupResponse();
        bloodGroupResponse.setAccepts(splitAndReturnList(getAccepts()));
        bloodGroupResponse.setGives(splitAndReturnList(getGives()));
        bloodGroupResponse.setName(getName());
        return bloodGroupResponse;
    }

    private List<String> splitAndReturnList(String string) {
        return Arrays.asList(string.split("\\,"));
    }

    private String convertListToString(List<String> stringList) {
        return String.join(",", stringList);
    }
}
