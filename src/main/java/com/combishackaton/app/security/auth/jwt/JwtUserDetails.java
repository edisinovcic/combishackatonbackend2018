package com.combishackaton.app.security.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserDetails extends UserDetails {

    String getEmail();
}
