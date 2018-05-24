package com.combishackaton.app.features.bloodgroups.entity;

import com.combishackaton.app.common.model.BaseEntity;
import com.combishackaton.app.common.model.TransferEntity;
import com.combishackaton.app.features.bloodgroups.model.BloodGroupResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "blood_groups")
@Data
public class BloodGroup extends BaseEntity implements TransferEntity<BloodGroupResponse> {

    @Column(name = "name")
    private String name;

    @Column(name = "accepts")
    private String accepts;

    @Column(name = "gives")
    private String gives;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

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
