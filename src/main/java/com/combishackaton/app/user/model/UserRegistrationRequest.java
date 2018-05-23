package com.combishackaton.app.user.model;

import com.combishackaton.app.user.entity.User;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String yearOfBirth;
    private String gender;
    private String address;
    private String bloodType;
    private String password;

    public User fillUser() {
        User user = new User();
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setGender(this.getGender());
        user.setAddress(this.getAddress());
        user.setAuthorityGroup("CUSTOMER");
        return user;
    }
}

