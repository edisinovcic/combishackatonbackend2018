package com.combishackaton.app.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestFieldError implements Serializable {

    private static final long serialVersionUID = 242385203343245L;

    private String fieldName;

    private String message;
}
