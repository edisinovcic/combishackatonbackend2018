package com.combishackaton.app.features.bloodgroup.controller;

import com.combishackaton.app.common.model.RestResponse;
import com.combishackaton.app.features.bloodgroup.entity.BloodGroup;
import com.combishackaton.app.features.bloodgroup.exception.BloodGroupNotFoundException;
import com.combishackaton.app.features.bloodgroup.model.BloodGroupResponse;
import com.combishackaton.app.features.bloodgroup.service.BloodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blood-group")
public class BloodGroupController {

    private BloodGroupService bloodGroupService;

    @Autowired
    public BloodGroupController(BloodGroupService bloodGroupService) {
        this.bloodGroupService = bloodGroupService;
    }


    @GetMapping("/all")
    public RestResponse<List<BloodGroupResponse>> fetchAll() {
        return new RestResponse<List<BloodGroupResponse>>(true).setData(
                bloodGroupService.getAllBloodGroups().stream().map(BloodGroup::getTransferObject)
                                 .collect(Collectors.toList()));
    }


    @GetMapping("/{name}")
    public RestResponse<BloodGroupResponse> fetch(@PathVariable(value = "name") String name) throws
            BloodGroupNotFoundException {
        return new RestResponse<BloodGroupResponse>(true)
                .setData(bloodGroupService.getByBloodGroupName(name).getTransferObject());
    }


}
