package com.combishackaton.app.configuration.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Configuration extends AuditableEntity {

    @Column(name = "conf_key")
    private String key;

    @Enumerated(EnumType.STRING)
    @Column(name = "conf_type")
    private ConfigurationType type;

    @Column(name = "conf_value")
    private String value;
}
