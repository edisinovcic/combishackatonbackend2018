package com.combishackaton.app.features.bloodgroup.service;

import com.combishackaton.app.features.bloodgroup.entity.BloodGroup;
import com.combishackaton.app.features.bloodgroup.exception.BloodGroupNotFoundException;

import java.util.List;

public interface BloodGroupService {

    BloodGroup getByBloodGroupName(String name) throws BloodGroupNotFoundException;

    List<BloodGroup> getAllBloodGroups();

}
