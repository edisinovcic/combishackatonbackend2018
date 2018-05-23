package com.combishackaton.app.features.bloodstock.service;

import com.combishackaton.app.features.bloodstock.entity.BloodStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, String> {

    List<BloodStock> findAllByBloodGroup(String bloodGroup);

    List<BloodStock> findAllByCreatedAtAfterAndCreatedAtBefore(LocalDateTime start, LocalDateTime end);

}
