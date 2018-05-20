package com.combishackaton.app.logging.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Builder
@Entity
@Table(name = "exception_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Valid
    private Long id;

    @NotNull
    @Column(name = "exception_name")
    private String exceptionName;

    @Column(name = "occurred_at")
    private LocalDateTime timestamp;

    @NotNull
    @Column(name = "stack_trace", columnDefinition = "varchar(65536)", length = 65536)
    private String stackTrace;

    @Column(name = "[user]")
    private String user;
}
