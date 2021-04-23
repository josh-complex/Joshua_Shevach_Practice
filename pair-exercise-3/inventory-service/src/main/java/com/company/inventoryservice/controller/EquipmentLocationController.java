package com.company.inventoryservice.controller;

import com.company.inventoryservice.dao.EquipmentLocationRepository;
import com.company.inventoryservice.model.EquipmentLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipment")
@RefreshScope
public class EquipmentLocationController {

    @Autowired
    EquipmentLocationRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentLocation createNewLocation(@RequestBody EquipmentLocation location) {
        return repo.save(location);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<EquipmentLocation> getEquipmentLocation(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentLocation> getEquipmentLocations(@RequestParam(required = false) String description) {
        if(description != null) {
            return repo.findAllByDescription(description);
        } else {
            return repo.findAll();
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEquipmentLocation(@RequestBody EquipmentLocation location) {
        repo.save(location);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipmentLocation(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
