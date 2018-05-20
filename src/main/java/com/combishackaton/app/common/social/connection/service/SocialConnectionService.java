package com.combishackaton.app.common.social.connection.service;


import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import com.combishackaton.app.user.entity.User;

public interface SocialConnectionService {

    SocialConnection findById(String socialConnectionId);

    SocialConnection findBySocialIdAndSocialType(String socialId, SocialType socialType);

    SocialConnection create(String socialId, SocialType socialType, User user);

    SocialConnection save(SocialConnection socialConnection);

    void delete(SocialConnection socialConnection);

    void delete(String socialConnectionId);
}
