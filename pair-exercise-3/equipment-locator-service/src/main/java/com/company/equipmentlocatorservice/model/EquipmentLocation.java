package com.company.equipmentlocatorservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EquipmentLocation {

    private Integer id;
    @NotBlank(message = "Must supply a description")
    @Size(max = 40)
    private String description;
    @NotBlank(message = "Must supply a location")
    @Size(max = 40)
    private String location;

}