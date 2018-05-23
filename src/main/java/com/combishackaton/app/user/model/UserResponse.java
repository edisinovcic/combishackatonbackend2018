package com.combishackaton.app.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String yearOfBirth;
    private String gender;
    private String address;
    private String bloodType;
    private String authorityGroup;
    private String password;
}
