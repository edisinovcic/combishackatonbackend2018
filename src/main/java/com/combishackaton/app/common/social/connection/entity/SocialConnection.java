package com.combishackaton.app.common.social.connection.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import com.combishackaton.app.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "social_connection")
@Data
public class SocialConnection extends AuditableEntity {

    @Column(name = "social_id")
    private String socialId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
