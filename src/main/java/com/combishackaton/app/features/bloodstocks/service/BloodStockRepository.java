package com.combishackaton.app.features.bloodstocks.service;

import com.combishackaton.app.features.bloodstocks.entity.BloodStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, String> {

    List<BloodStock> findAllByBloodGroup(String bloodGroup);

    List<BloodStock> findAll();

}
