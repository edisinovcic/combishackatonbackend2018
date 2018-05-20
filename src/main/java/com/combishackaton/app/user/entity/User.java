package com.combishackaton.app.user.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import com.combishackaton.app.common.model.TransferEntity;
import com.combishackaton.app.user.model.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
public class User extends AuditableEntity implements TransferEntity<UserResponse> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String authorityGroup;

    @JsonIgnore
    @Override
    public UserResponse getTransferObject() {
        return UserResponse.builder()
                           .id(getId())
                           .email(getEmail())
                           .firstName(getFirstName())
                           .lastName(getLastName())
                           .gender(getGender())
                           .authorityGroup(getAuthorityGroup())
                           .build();
    }
}
