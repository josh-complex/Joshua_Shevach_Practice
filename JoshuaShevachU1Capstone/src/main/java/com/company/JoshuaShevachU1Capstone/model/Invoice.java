package com.company.JoshuaShevachU1Capstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @NotNull
    private int id;
    @NotNull
    @Size(max = 80)
    private String name;
    @NotNull
    @Size(max = 30)
    private String street;
    @NotNull
    @Size(max = 20)
    private String city;
    @NotNull
    @Size(max = 2)
    private String state;
    @NotNull
    @Size(max = 5)
    private String zipcode;
    @NotNull
    @Size(max = 50)
    private String itemType;
    @NotNull
    private int itemId;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal unitPrice;
    @NotNull
    @Positive
    private int quantity;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal subtotal;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal tax;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal processingFee;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal total;
}
