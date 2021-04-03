package com.company.JoshuaShevachU1Capstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesTaxRate {

    @NotNull
    @Size(max = 2)
    private String state;
    @NotNull
    @Digits(integer = 3, fraction = 2)
    private BigDecimal rate;

}
