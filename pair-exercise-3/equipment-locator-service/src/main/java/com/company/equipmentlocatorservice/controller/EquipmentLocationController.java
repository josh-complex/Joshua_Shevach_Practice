package com.company.equipmentlocatorservice.controller;

import com.company.equipmentlocatorservice.feign.EquipmentLocationClient;
import com.company.equipmentlocatorservice.model.EquipmentLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@RefreshScope
public class EquipmentLocationController {

    @Autowired
    private EquipmentLocationClient client;

    @PutMapping
    public void updateEquipmentLocation(@RequestBody EquipmentLocation location) {
        client.updateEquipmentLocation(location);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentLocation> getEquipmentLocations(@RequestParam(required = false) String description) {
        return client.getEquipmentLocations(description);
    }

}
