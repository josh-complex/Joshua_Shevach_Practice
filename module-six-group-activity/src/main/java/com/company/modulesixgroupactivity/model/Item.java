package com.company.modulesixgroupactivity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @NotEmpty
    @Size(max = 11)
    private int itemId;
    @NotEmpty
    @Size(max = 50)
    private String name;
    @NotEmpty
    @Size(max = 255)
    private String description;
    @NotNull
    @Digits(integer = 8, fraction = 2)
    private BigDecimal dailyRate;

}
