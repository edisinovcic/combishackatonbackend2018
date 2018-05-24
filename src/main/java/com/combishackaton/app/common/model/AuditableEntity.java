package com.combishackaton.app.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity extends BaseEntity {

    @JsonIgnore
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
