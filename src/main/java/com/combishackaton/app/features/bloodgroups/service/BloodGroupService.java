package com.combishackaton.app.features.bloodgroups.service;

import com.combishackaton.app.features.bloodgroups.entity.BloodGroup;
import com.combishackaton.app.features.bloodgroups.exception.BloodGroupNotFoundException;

import java.util.List;

public interface BloodGroupService {

    BloodGroup getByBloodGroupName(String name) throws BloodGroupNotFoundException;

    List<BloodGroup> getAllBloodGroups();

}
