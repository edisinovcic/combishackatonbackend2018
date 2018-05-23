package com.combishackaton.app.features.bloodstocks.service;


import com.combishackaton.app.features.bloodstocks.entity.BloodStock;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodStockService {

    List<BloodStock> findAllBetweenTimestamp(LocalDateTime start, LocalDateTime end);

    List<BloodStock> findAll();

    List<BloodStock> findAllByBloodGroup(String bloodGroup);

    BloodStock findById(String id);


}
