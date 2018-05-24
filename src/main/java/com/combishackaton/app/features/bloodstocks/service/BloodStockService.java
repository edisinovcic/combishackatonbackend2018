package com.combishackaton.app.features.bloodstocks.service;


import com.combishackaton.app.features.bloodstocks.entity.BloodStock;
import com.combishackaton.app.features.bloodstocks.model.BloodStockRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodStockService {

    List<BloodStock> findAllBetweenTimestamp(LocalDateTime start, LocalDateTime end);

    List<BloodStock> findAll();

    List<BloodStock> findAllByBloodGroup(String bloodGroup);

    BloodStock findById(String id);

    BloodStock create(BloodStockRequest bloodStockRequest);

    BloodStock update(BloodStockRequest bloodStockRequest, String id);


}
