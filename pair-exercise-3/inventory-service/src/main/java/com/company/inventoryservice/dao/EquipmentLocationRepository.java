package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.EquipmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentLocationRepository extends JpaRepository<EquipmentLocation, Integer> {

    List<EquipmentLocation> findAllByDescription(String description);

}
