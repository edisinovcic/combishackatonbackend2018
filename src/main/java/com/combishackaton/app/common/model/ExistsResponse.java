package com.combishackaton.app.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExistsResponse {

    boolean exists;

    public ExistsResponse(boolean exists) {
        this.exists = exists;
    }
}
