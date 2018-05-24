package com.combishackaton.app.features.bloodstocks.service;

import com.combishackaton.app.features.bloodstocks.entity.BloodStock;
import com.combishackaton.app.features.bloodstocks.model.BloodStockRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BloodStockServiceImpl implements BloodStockService {

    private BloodStockRepository bloodStockRepository;

    @Autowired
    public BloodStockServiceImpl(BloodStockRepository bloodStockRepository) {
        this.bloodStockRepository = bloodStockRepository;
    }

    @Override
    public List<BloodStock> findAllBetweenTimestamp(LocalDateTime start, LocalDateTime end) {
        return bloodStockRepository.findAllByCreatedAtAfterAndCreatedAtBefore(start, end);
    }

    @Override
    public List<BloodStock> findAll() {
        return bloodStockRepository.findAll();
    }

    @Override
    public List<BloodStock> findAllByBloodGroup(String bloodGroup) {
        return bloodStockRepository.findAllByBloodGroup(bloodGroup);
    }

    @Override
    public BloodStock findById(String id) {
        return Optional.ofNullable(bloodStockRepository.findOne(id))
                       .orElseThrow(() -> new EntityNotFoundException("Blood stock with id: " + id + " not found"));
    }

    @Override
    public BloodStock create(BloodStockRequest bloodStockRequest) {
        return bloodStockRepository.save(bloodStockRequest.toBloodStock());
    }

    @Override
    public BloodStock update(BloodStockRequest bloodStockRequest, String id) {
        BloodStock bloodStock = bloodStockRequest.toBloodStock();
        bloodStock.setId(bloodStockRepository.findOne(id).getId());
        return bloodStockRepository.save(bloodStock);
    }
}
