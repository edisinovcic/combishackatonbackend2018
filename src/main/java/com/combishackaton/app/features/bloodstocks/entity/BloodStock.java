package com.combishackaton.app.features.bloodstocks.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "blood_stocks")
@Data
public class BloodStock extends AuditableEntity {//implements TransferEntity<BloodStockResponse> {

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "amount")
    private Integer amount;

   /*
    @Override
    public BloodStockResponse getTransferObject() {
        BloodStockResponse bloodStockResponse = new BloodStockResponse();
        bloodStockResponse.setAmount(getAmount());
        bloodStockResponse.setBloodGroup(getBloodGroup());
        return bloodStockResponse;
    }
    */
}
