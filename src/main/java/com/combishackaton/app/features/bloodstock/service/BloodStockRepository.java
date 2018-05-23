package com.combishackaton.app.features.bloodstock.service;

import com.combishackaton.app.features.bloodstock.entity.BloodStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, String> {
}
