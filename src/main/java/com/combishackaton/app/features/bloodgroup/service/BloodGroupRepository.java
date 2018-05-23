package com.combishackaton.app.features.bloodgroup.service;

import com.combishackaton.app.features.bloodgroup.entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, String> {

    BloodGroup findByName(String name);
}
