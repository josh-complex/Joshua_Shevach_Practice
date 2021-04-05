package com.company.JoshuaShevachU1Capstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesTaxRate {

    @NotNull(message = "Must supply a state")
    @Size(min = 2, max = 2, message = "Must supply a valid 2-character state code")
    private String state;
    @NotNull(message = "Must supply a rate")
    @Positive(message = "Tax rate must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than 99,999.99")
    private BigDecimal rate;

}
