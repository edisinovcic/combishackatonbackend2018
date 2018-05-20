package com.combishackaton.app.common.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid2")
    private String id;

    public String getId() {
        return (id != null) ? id.toLowerCase() : null;
    }

    public void setId(String id) {
        this.id = (id != null) ? id.toLowerCase() : null;
    }
}
