package com.combishackaton.app.logging.entity;

import com.combishackaton.app.user.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Data
@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Valid
    private Long id;

    private String action;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime time;

    @Column(name = "isSuccess")
    private boolean success;
    private String error;
    private String device;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
