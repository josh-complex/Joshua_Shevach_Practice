package com.company.JoshuaShevachU1Capstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @NotNull
    protected int id;
    @Size(max = 20)
    protected String itemType;
    @NotNull
    @PositiveOrZero
    protected int remainingInventory;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    protected BigDecimal price;

}
