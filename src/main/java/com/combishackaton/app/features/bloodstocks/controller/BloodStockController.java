package com.combishackaton.app.features.bloodstocks.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.bloodstocks.entity.BloodStock;
import com.combishackaton.app.features.bloodstocks.model.BloodStockResponse;
import com.combishackaton.app.features.bloodstocks.service.BloodStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blood-stock")
public class BloodStockController {

    private BloodStockService bloodStockService;

    @Autowired
    public BloodStockController(BloodStockService bloodStockService) {
        this.bloodStockService = bloodStockService;
    }

    @GetMapping("/all")
    public RestResponse<List<BloodStockResponse>> fetchAll() {
        return new RestResponse<List<BloodStockResponse>>(true).setData(
                bloodStockService.findAll().stream().map(BloodStock::getTransferObject).collect(Collectors.toList()));
    }


    @GetMapping("/blood-group/{bloodGroupName}")
    public RestResponse<List<BloodStockResponse>> fetchAllByBloodGroup(
            @PathVariable(value = "bloodGroupName") String bloodGroupName) {
        return new RestResponse<List<BloodStockResponse>>(true).setData(
                bloodStockService.findAllByBloodGroup(bloodGroupName).stream().map(BloodStock::getTransferObject)
                                 .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public RestResponse<BloodStockResponse> fetchAll(@PathVariable(value = "id") String id) {
        return new RestResponse<BloodStockResponse>(true).setData(bloodStockService.findById(id).getTransferObject());
    }

    @GetMapping("/between")
    public RestResponse<List<BloodStockResponse>> fetchAllBetween(LocalDateTime start, LocalDateTime end) {
        return new RestResponse<List<BloodStockResponse>>(true).setData(
                bloodStockService.findAllBetweenTimestamp(start, end).stream().map(BloodStock::getTransferObject)
                                 .collect(Collectors.toList()));
    }
}
