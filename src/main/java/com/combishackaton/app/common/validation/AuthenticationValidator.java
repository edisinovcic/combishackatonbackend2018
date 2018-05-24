package com.combishackaton.app.common.validation;

import com.combishackaton.app.common.exception.InsufficientPriviledgesException;
import com.combishackaton.app.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationValidator {

    public void checkAuthenticatedUserIsAdmin(User authenticatedUser) throws InsufficientPriviledgesException {
        if(!authenticatedUser.getAuthorityGroup().contentEquals("ADMIN")) {
            throw new InsufficientPriviledgesException("You must be admin to access this!");
        }
    }


}
