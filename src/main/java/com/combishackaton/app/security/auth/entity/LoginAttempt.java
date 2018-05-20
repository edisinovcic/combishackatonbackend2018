package com.combishackaton.app.security.auth.entity;

import com.combishackaton.app.user.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class LoginAttempt {

    public LoginAttempt(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Valid
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastModified;

    @Setter(value = AccessLevel.NONE)
    private int attempts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void incrementAttempt() {
        ++this.attempts;
    }

    @PrePersist
    @PreUpdate
    protected void onPersist() {
        this.lastModified = LocalDateTime.now();
    }

}
