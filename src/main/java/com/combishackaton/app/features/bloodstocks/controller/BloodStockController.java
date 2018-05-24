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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
    public RestResponse<List<BloodStock>> fetchAll() throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<BloodStock>>(true).setData(
                bloodStockService.findAll());
    }

    @GetMapping("/{id}")
    public RestResponse<BloodStock> fetchAll(@PathVariable(value = "id") String id) throws
            InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<BloodStock>(true).setData(bloodStockService.findById(id));
    }

    @GetMapping("/blood-group/{bloodGroupName}")
    public RestResponse<List<BloodStock>> fetchAllByBloodGroup(
            @PathVariable(value = "bloodGroupName") String bloodGroupName) throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        return new RestResponse<List<BloodStock>>(true).setData(
                bloodStockService.findAllByBloodGroup(bloodGroupName));
    }

    @GetMapping("/between")
    public RestResponse<List<BloodStock>> fetchAllBetween(String s, String e) throws InsufficientPriviledgesException {
        authenticationValidator.checkAuthenticatedUserIsAdmin(userService.getAuthenticatedUser());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime start = LocalDateTime
                .of(LocalDate.parse(s, dateTimeFormatter), LocalDateTime.now().toLocalTime());
        LocalDateTime end = LocalDateTime.of(LocalDate.parse(e, dateTimeFormatter), LocalDateTime.now().toLocalTime());
        return new RestResponse<List<BloodStock>>(true).setData(bloodStockService.findAllBetweenTimestamp(start, end));
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
