package com.combishackaton.app.common.social.connection.service;


import com.combishackaton.app.common.social.connection.entity.SocialConnection;
import com.combishackaton.app.common.social.connection.entity.SocialType;
import com.combishackaton.app.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SocialConnectionServiceImpl implements SocialConnectionService {

    private SocialConnectionRepository socialConnectionRepository;

    @Autowired
    public SocialConnectionServiceImpl(SocialConnectionRepository socialConnectionRepository) {
        this.socialConnectionRepository = socialConnectionRepository;
    }

    @Override
    public SocialConnection findById(String socialConnectionId) {
        return Optional.ofNullable(socialConnectionRepository.findById(socialConnectionId)).orElseThrow(
                () -> new EntityNotFoundException("Could not find social connection with given id"));
    }

    @Override
    public SocialConnection create(String socialId, SocialType socialType, User user) {
        SocialConnection socialConnection = new SocialConnection();
        socialConnection.setSocialId(socialId);
        socialConnection.setSocialType(socialType);
        socialConnection.setUser(user);
        return save(socialConnection);
    }

    @Override
    public SocialConnection findBySocialIdAndSocialType(String socialId, SocialType socialType) {
        return socialConnectionRepository.findBySocialIdAndSocialType(socialId, socialType);
    }

    @Override
    public SocialConnection save(SocialConnection socialConnection) {
        return socialConnectionRepository.save(socialConnection);
    }

    @Override
    public void delete(SocialConnection socialConnection) {
        socialConnectionRepository.delete(socialConnection);
    }

    @Override
    public void delete(String socialConnectionId) {
        socialConnectionRepository.delete(socialConnectionId);
    }
}
