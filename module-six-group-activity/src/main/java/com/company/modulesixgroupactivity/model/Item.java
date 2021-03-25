package com.company.modulesixgroupactivity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int itemId;
    private String name;
    private String description;
    private BigDecimal dailyRate;

}
