package com.combishackaton.app.common.social.connection.service;

import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialConnectionRepository extends JpaRepository<SocialConnection, String> {

    SocialConnection findBySocialIdAndSocialType(String socialId, SocialType socialType);

    SocialConnection findById(String socialConnectionId);
}
