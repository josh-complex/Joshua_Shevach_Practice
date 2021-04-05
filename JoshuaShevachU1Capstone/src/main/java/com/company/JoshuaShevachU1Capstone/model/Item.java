package com.company.JoshuaShevachU1Capstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @NotNull(message = "Must supply an ID")
    protected int id;

    @NotNull(message = "Must supply a remaining inventory")
    @PositiveOrZero(message = "Remaining inventory cannot be negative")
    protected int remainingInventory;

    @NotNull(message = "Must supply a price")
    @Positive(message = "Price must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    protected BigDecimal price;

}
