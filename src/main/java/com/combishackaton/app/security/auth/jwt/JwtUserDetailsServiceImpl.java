package com.combishackaton.app.security.auth.jwt;

import com.combishackaton.app.security.auth.model.UserPrincipal;
import com.combishackaton.app.user.entity.User;
import com.combishackaton.app.user.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public JwtUserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user with username " + username);
        }
        return new UserPrincipal(user);
    }
}
