package com.combishackaton.app.user.entity;

import com.combishackaton.app.common.model.AuditableEntity;
import com.combishackaton.app.common.model.TransferEntity;
import com.combishackaton.app.user.model.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
public class User extends AuditableEntity implements TransferEntity<UserResponse> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "authority_group")
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
