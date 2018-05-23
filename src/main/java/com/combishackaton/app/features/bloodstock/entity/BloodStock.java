package com.combishackaton.app.features.bloodstock.entity;

import com.combishackaton.app.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "blood_stocks")
@Data
public class BloodStock extends BaseEntity {

    @Column(name = "blood_group")
    private String blood_group;

    @Column(name = "amount")
    private Integer amount;

}
