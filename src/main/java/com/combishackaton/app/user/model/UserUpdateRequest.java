package com.combishackaton.app.user.model;

import com.combishackaton.app.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String yearOfBirth;
    private String gender;
    private String address;
    private String bloodType;
    private String password;

    public User toUser(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPhoneNumber(this.phoneNumber);
        user.setEmail(this.email);
        user.setYearOfBirth(this.yearOfBirth);
        user.setGender(this.gender);
        user.setAddress(this.address);
        user.setBloodType(this.bloodType);
        user.setAuthorityGroup("CUSTOMER");
        return user;
    }

}
