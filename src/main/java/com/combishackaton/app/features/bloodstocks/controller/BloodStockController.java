package com.combishackaton.app.features.bloodstocks.controller;

import com.combishackaton.app.common.exception.InsufficientPriviledgesException;
import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.common.validation.AuthenticationValidator;
import com.combishackaton.app.features.bloodstocks.entity.BloodStock;
import com.combishackaton.app.features.bloodstocks.model.BloodStockRequest;
import com.combishackaton.app.features.bloodstocks.model.BloodStockResponse;
import com.combishackaton.app.features.bloodstocks.service.BloodStockService;
import com.combishackaton.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blood-stock")
public class BloodStockController {

    private BloodStockService bloodStockService;
    private UserService userService;
    private AuthenticationValidator authenticationValidator;

    @Autowired
    public BloodStockController(BloodStockService bloodStockService, UserService userService,
            AuthenticationValidator authenticationValidator) {
        this.bloodStockService = bloodStockService;
        this.userService = userService;
        this.authenticationValidator = authenticationValidator;
    }

    @GetMapping("/all")
    public RestResponse<List<BloodStockResponse>> fetchAll() throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<BloodStockResponse>>(true).setData(
                bloodStockService.findAll().stream().map(BloodStock::getTransferObject).collect(Collectors.toList()));
    }


    @GetMapping("/blood-group/{bloodGroupName}")
    public RestResponse<List<BloodStockResponse>> fetchAllByBloodGroup(
            @PathVariable(value = "bloodGroupName") String bloodGroupName) throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<BloodStockResponse>>(true).setData(
                bloodStockService.findAllByBloodGroup(bloodGroupName).stream().map(BloodStock::getTransferObject)
                                 .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public RestResponse<BloodStockResponse> fetchAll(@PathVariable(value = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<BloodStockResponse>(true).setData(bloodStockService.findById(id).getTransferObject());
    }

    @GetMapping("/between")
    public RestResponse<List<BloodStockResponse>> fetchAllBetween(LocalDateTime start, LocalDateTime end) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<BloodStockResponse>>(true).setData(
                bloodStockService.findAllBetweenTimestamp(start, end).stream().map(BloodStock::getTransferObject)
                                 .collect(Collectors.toList()));
    }

    @PostMapping
    public RestResponse<BloodStock> create(@RequestBody BloodStockRequest bloodStockRequest) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<BloodStock>(true).setData(bloodStockService.create(bloodStockRequest));
    }

    @PutMapping("/{id}")
    public RestResponse<BloodStock> update(@PathVariable(value = "id") String id,
            @RequestBody BloodStockRequest bloodStockRequest) throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<BloodStock>(true).setData(bloodStockService.update(bloodStockRequest, id));
    }

}
