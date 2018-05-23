package com.combishackaton.app.features.bloodgroup.service;

import com.combishackaton.app.features.bloodgroup.entity.BloodGroup;
import com.combishackaton.app.features.bloodgroup.exception.BloodGroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodGroupServiceImpl implements BloodGroupService {


    private BloodGroupRepository bloodGroupRepository;

    @Autowired
    public BloodGroupServiceImpl(BloodGroupRepository bloodGroupRepository) {
        this.bloodGroupRepository = bloodGroupRepository;
    }

    @Override
    public BloodGroup getByBloodGroupName(String name) throws BloodGroupNotFoundException {
        return Optional.ofNullable(bloodGroupRepository.findByName(name))
                       .orElseThrow(() -> new BloodGroupNotFoundException("Blood group " + name + " not found"));
    }

    @Override
    public List<BloodGroup> getAllBloodGroups() {
        return bloodGroupRepository.findAll();
    }
}
