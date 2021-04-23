package com.company.equipmentlocatorservice.feign;

import com.company.equipmentlocatorservice.model.EquipmentLocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface EquipmentLocationClient {

    @GetMapping("/equipment")
    List<EquipmentLocation> getEquipmentLocations(@RequestParam(required = false) String description);

    @PutMapping("/equipment")
    void updateEquipmentLocation(@RequestBody EquipmentLocation location);

}
